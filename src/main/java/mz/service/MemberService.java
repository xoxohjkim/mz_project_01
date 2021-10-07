package mz.service;

import org.springframework.stereotype.Service;

import mz.dto.Member;

@Service
public interface MemberService {
	
	int registerMember(Member member);
	Member login(String id, String pwd);
}
