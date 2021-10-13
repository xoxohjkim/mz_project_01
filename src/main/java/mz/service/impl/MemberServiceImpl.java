package mz.service.impl;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import mz.dto.Mail;
import mz.dto.Member;
import mz.mapper.MemberMapper;
import mz.service.MailService;
import mz.service.MemberService;
import mz.util.SHA256Util;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private MailService mailService;
	
	@Transactional
	@Override
	public int registerMember(Member member) {
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		
		int authKey = Integer.parseInt(numStr);
		member.setAuthKey(authKey);
		log.info(" authKey : " + authKey);
		int res = mapper.insertMember(member);
		
		log.info("메일 전송");
		sendAuthMail(member.getEmail(), authKey);
		
		return res;
	}


	public void sendAuthMail(String email, int authKey) {
		
		Mail mail = new Mail();
	    mail.setMailFrom("project.mz.hjkim@gmail.com");
	    mail.setMailTo(email);
	    mail.setMailSubject("[회원가입 이메일 인증]");
	    mail.setMailContent(
	    		new StringBuffer().append("<h1>[이메일 인증]</h1>")
	            .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
	            .append("<a href='http://localhost:8080/confirm?email=")
	            .append(email)
	            .append("&authKey=")
	            .append(authKey)
	            .append("' target='_blenk'>이메일 인증 확인</a>")
	            .toString());

	    mailService.sendEmail(mail);
		
	}
	
	
	@Override
	public Member login(String id, String pwd) {
		return mapper.login(id, pwd);
	}

	@Override
	public String findMemberByCondition(Member member) {
		String id = mapper.findMemberByCondition(member);
		return id;
	}
	
	public static String masking(String id) {
		id = id.replaceAll("(?<=.{3}).", "*");
		return id;
	}

	@Transactional
	@Override
	public void sendPwdMail(Member member) {
		//1.임시비밀번호 생성
		//2.멤버 비번 변경
		//3.메일로 전송
		System.out.println(member);
		
		String pwd = null;
		for (int i = 0;i < 5; i++) {
			pwd = UUID.randomUUID().toString().replaceAll("-", ""); // '-'제거
		     pwd = pwd.substring(0, 8);
	
		}
		member.setPwd(pwd);
		int res =mapper.updateMember(member);
		System.out.println("pwd res?" + res);
	
		Mail mail = new Mail();
	    mail.setMailFrom("project.mz.hjkim@gmail.com");
	    mail.setMailTo(member.getEmail());
	    mail.setMailSubject("[임시 비밀번호 안내]");
	    mail.setMailContent(member.getId() + "님의 " + "임시 비밀번호는 [ " + pwd + " ]입니다. <br>"
	    		+ "로그인하여 변경해주세요.");

	    mailService.sendEmail(mail);
			
	}
	


	@Override
	public int updateMember(Member member) {
		
		System.out.println("member:" + member);
		String pwd = member.getPwd();
		String salt = getSaltByMemberId(member.getId());
		
		pwd = SHA256Util.getEncrypt(pwd, salt);
		member.setPwd(pwd);
		
		//auth_state 어떻게 처리할 지 고민해봐야함
		member.setAuthState(1);
		
		return mapper.updateMember(member);
	}

	@Override
	public int deleteMember(Member member) {
		return mapper.deleteMember(member);
	}


	@Override
	public Member signUpConfirmById(String email, String authKey) {
		return mapper.signUpConfirmById(email, authKey);
	}


	@Override
	public String getSaltByMemberId(String id) {
		return mapper.getSaltByMemberId(id);
	}


	@Override
	public int infoCheck(String condition, String keyword) {
		return mapper.infoCheck(condition, keyword);
	}



	

}
