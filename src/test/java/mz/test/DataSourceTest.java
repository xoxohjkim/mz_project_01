package mz.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import mz.config.RootConfig;
import mz.dto.Member;
import mz.mapper.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Log4j
public class DataSourceTest {
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory ssf;
	
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testConnection() {
		
		try (Connection con = ds.getConnection(); 
				SqlSession session = ssf.openSession()) {
			log.info(con);
			log.info(session);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mybatisTest() {
		System.out.println(mapper.getTime());
	}
	
	//@Test
	public void memberTest() {
		//log.info(mapper.selectMemberAll());
		Member member = new Member();
		member.setId("test234");
		member.setPwd("ehqt1334");
		member.setEmail("sdfsdf@dhf.com");
		member.setName("�??��?��");
		member.setNickname("?��?��");
		member.setAuthKey(324234);
		
		int res = mapper.insertMember(member);
		log.info(res);
	}
	
	
	
}
