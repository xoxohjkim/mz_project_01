package mz.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import mz.dto.Member;
import mz.mapper.MemberMapper;
import mz.service.MemberService;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public int registerMember(Member member) {
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		
		int num = Integer.parseInt(numStr);
		member.setAuthKey(num);
		log.info(" authKey : " + num);
		int res = mapper.insertMember(member);
		return res;
	}

	@Override
	public Member login(String id, String pwd) {
		return mapper.login(id, pwd);
	}

	@Override
	public String findMemberByCondition(Member member) {
		String id = mapper.findMemberByCondition(member);
		String maskingId = id.replaceAll("(?<=.{3}).", "*");
		return maskingId;
	}

	

}
