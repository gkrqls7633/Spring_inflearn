package com.example.hello.hellospring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello.hellospring.repository.JdbcMemberRepository;
import com.example.hello.hellospring.repository.MemberRepository;
import com.example.hello.hellospring.repository.MemoryMemberRepository;
import com.example.hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	
	@Autowired DataSource dataSource;
	
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	

	@Bean
	public MemberRepository memberRepository() {
		return new JdbcTemplateMemberRepository(dataSource);
	}
}
