package mz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mz.dto.Member;

public interface MemberMapper {
	String getTime();
	Member read(String userid);
	
	
	List<Member> selectMemberAll();
	Member selectMemberById(@Param("id")String id);
	
	Member login(@Param("id")String id, @Param("pwd")String pwd);
	int insertMember(Member member);
	int updateMember(Member member);
	int deleteMember(Member member);
	
	
	
	
}
