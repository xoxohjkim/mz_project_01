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
import mz.service.MemberService;

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
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		
		String id = (String) data.get("id");
		String pwd = (String) data.get("pwd");
		System.out.println(id + " / " + pwd);
		
		Member member = service.login(id, pwd);
		if(member != null) {
			//boolean pwMatch = passwordEncoder.matches(pwd, member.getPwd());
			//log.info("pwMatches:" + pwMatch);
				session.setAttribute("loginUser", member);
				log.info("λ‘κ·Έ?Έ?±κ³?");
				return 1;
			
		} else {
			log.info("λ‘κ·Έ?Έ ?€?¨");
			request.setAttribute("msg", "??΄?? λΉλ?λ²νΈκ°? λ§μ? ??΅??€.");
			return 0;
		}
	
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		log.info("λ‘κ·Έ??");
		return "redirect:/main";
	}
	
	@GetMapping("/register")
	public String register() {
		log.info("??κ°?? form");
		return "register";
	}
	
	@ResponseBody
	@PostMapping("/register")
	public int doRegister(Member member) {
		System.out.println(member);
			
		//String password = passwordEncoder.encode(member.getPwd());
		//member.setPwd(password);
		//log.info("λΉλ²:" + password);
		int res = service.registerMember(member);
		return res;
	}
	
	
}
