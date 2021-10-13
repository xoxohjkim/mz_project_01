package mz.service;

import org.springframework.stereotype.Service;

import mz.dto.Member;

@Service
public interface MemberService {
	
	int registerMember(Member member);
	int updateMember(Member member);
	int deleteMember(Member member);
	
	Member login(String id, String pwd);
	
	String findMemberByCondition(Member member);
	void sendPwdMail(Member member);
	
	Member signUpConfirmById(String email, String authKey);
	String getSaltByMemberId(String id);
	
	int infoCheck(String condition, String keyword);


}
