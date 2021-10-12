package mz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
				memberService.sendMail(member);
			}else {
				return "";
			}
			
		}
	
		return id;
	
	}
}
