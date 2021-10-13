package mz.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mz.dto.Member;
import mz.exceprtion.DuplicateMemberException;
import mz.service.MemberService;
import mz.util.SHA256Util;

@Controller
@Log4j
public class LoginController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@ResponseBody
	@PostMapping("/login")
	public int loginCheck(HttpServletRequest request, @RequestBody Map<String, Object> data) {
		
		HttpSession session = request.getSession();
		
		String id = (String) data.get("id");
		
		String pwd = (String) data.get("pwd");
		String salt = service.getSaltByMemberId(id);
		pwd = SHA256Util.getEncrypt(pwd, salt);

		Member member = service.login(id, pwd);

		int res = 0;
		
		if( member != null ) {
			//멤버가 존재하면 다시 암호화한 값을 비밀번호로 set
			member.setPwd(pwd);
			
			if(member.getAuthState() == 0) {
				log.info("이메일인증 필요");
				res = 2;
			}else if(member.getAuthState() == 1){
				log.info("로그인 완료");
				session.setAttribute("loginUser", member);
				res = 1;
			}
		}
		return res;

		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		log.info("로그아웃");
		return "redirect:/main";
	}
	
	
}
