package mz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mz.dto.Member;

public interface MemberMapper {
	//test
	String getTime();
	Member read(String userid);
	
	List<Member> selectMemberAll();
	Member selectMemberById(@Param("id")String id);
	
	Member signUpConfirmById(@Param("email")String email, @Param("authKey") String authKey);
	Member login(@Param("id")String id, @Param("pwd")String pwd);
	
	int insertMember(Member member);
	int updateMember(Member member);
	int deleteMember(Member member);
	
	String findMemberByCondition(Member member);
	String getSaltByMemberId(String id);
	
	int infoCheck(@Param("cond")String condition, @Param("key")String keyword);
	
}
