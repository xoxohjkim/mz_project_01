package mz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mz.dto.Member;
import mz.service.MemberService;
import mz.service.impl.MemberServiceImpl;
import mz.util.SHA256Util;

@Controller
@Log4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/register")
	public String register() {
		log.info("회원가입 form");
		return "register";
	}
	
	@ResponseBody
	@PostMapping("/register")
	public int doRegister(Member member) {
		System.out.println(member);
			
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);
		
		String password = member.getPwd();
		password = SHA256Util.getEncrypt(password, salt);
		member.setPwd(password);
		
		int res = memberService.registerMember(member);
		return res;
	}
	
	
	@ResponseBody
	@PostMapping("/find/{keyword}")
	public String match(@PathVariable String keyword, Member member) {
		log.info("아이디/비번찾기");
		
		String id = memberService.findMemberByCondition(member);
		
		if(keyword.equals("id")) {
			if(id != null) {
				String masking = MemberServiceImpl.masking(id);
				return masking;
			}
		}else if(keyword.equals("pwd")) {
			if(id != null) {
				memberService.sendPwdMail(member);
			}else {
				return "";
			}
			
		}
	
		return id;
	}
	
	@GetMapping("/confirm")
	public String signUpConfirm(Model model, @RequestParam String email, @RequestParam String authKey, HttpSession session) throws Exception {
		log.info("이메일인증 확인");
		Member member = memberService.signUpConfirmById(email, authKey);
		
		if(member != null) {
			member.setAuthState(1);
			memberService.updateMember(member);
			
			model.addAttribute("msg", "가입 인증이 완료되었습니다.");
			
		} else {
			model.addAttribute("msg", "잘못된 요청입니다.");
		}
		
		return "redirect";
	}
}
