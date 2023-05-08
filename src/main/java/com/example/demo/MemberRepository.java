package com.example.demo;

import com.example.demo.model.MemberDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 저장소라고 알려줌
public class MemberRepository { // Member 객체를 찾아주는 역할 => 저장소
    @PersistenceContext // 영속성 컨텍스트 생성
    private EntityManager em;

    public Long save(MemberDTO member){
        em.persist(member);
        return member.getId();
    }

    public MemberDTO find(Long id){ // 멤버 조회
        return em.find(MemberDTO.class, id);
    }
}
