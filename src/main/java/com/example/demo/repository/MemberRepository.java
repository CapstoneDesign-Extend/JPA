package com.example.demo.repository;

import com.example.demo.domain.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 자동으로 스프링 bean으로 사용됨
@RequiredArgsConstructor
public class MemberRepository {
    
    //@PersistenceContext
    private final EntityManager em;

//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    public void save(MemberDTO member){
        em.persist(member);
    }
    public MemberDTO findOne(Long id){
        return em.find(MemberDTO.class, id); // 해당 id로 member을 찾아줌
    }
    public List<MemberDTO> findAll(){ // 저장된 회원을 리스트 형식으로 찾음
        // JPA는 객체를 대상으로 쿼리문을 작성 => 메소드 인자 중 두 번째 인자가 타입을 나타냄
        List<MemberDTO> result = em.createQuery("select m from MemberDTO m", MemberDTO.class)
                .getResultList();
        return result;
    }
    public List<MemberDTO> findByStudentId(int studentId){ // 학번으로 회원을 찾음
        return em.createQuery("select m from MemberDTO m where m.studentId=:studentId", MemberDTO.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }
}
