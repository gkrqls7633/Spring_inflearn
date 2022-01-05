package com.example.hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository{

	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		em.persist(member);  //persist : 저장
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class,  id);
		return Optional.ofNullable(member);  //값이 없을 수도 있으므로 ofNullable 사용
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
				 return result.stream().findAny();
	}
	
	@Override
	public List<Member> findAll() {
		 return em.createQuery("select m from Member m", Member.class)
		 .getResultList();
		 }

}
