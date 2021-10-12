package mz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
@Log4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
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
	public String signUpConfirm(@RequestParam String email, @RequestParam String authKey, HttpServletResponse response) throws IOException {
		
		Member member = memberService.signUpConfirmById(email, authKey);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		
		if(member != null) {
			member.setAuthState(1);
			memberService.updateMember(member);
			
			out.println("<script language='javascript'>");
			out.println("alert('가입 인증이 완료되었습니다.');");
			out.println("location.href='/login'");
			out.println("</script>");
			out.flush();	
			
		} else {
			out.println("<script language='javascript'>");
			out.println("alert('잘못된 요청입니다.');");
			out.println("location.href='/login'");
			out.println("</script>");
			out.flush();
		}

		
		return "redirect:/";
	}
}
