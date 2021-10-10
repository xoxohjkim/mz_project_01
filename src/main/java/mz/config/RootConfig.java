package mz.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"mz.mapper"})
public class RootConfig {
	
	@Autowired
	 private ApplicationContext applicationContext;
	
	//connection pool
	@Bean
	public DataSource dataSource() {
		HikariConfig hikari = new HikariConfig();
		//hikari.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		//hikari.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		hikari.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		
		//집
		hikari.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:orcl?useUnicode=true&characterEncoding=UTF-8");
		
		//ㄴ회사
		//hikari.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		hikari.setUsername("hjkim");
		hikari.setPassword("rootroot");
		
		HikariDataSource ds = new HikariDataSource(hikari);
		
		return ds;
	}
	
	//MyBatis config
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setDataSource(dataSource);
		//factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/*Mapper.xml"));
		
		return factoryBean;
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
