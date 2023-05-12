package com.example.demo.repository;

import com.example.demo.domain.MemberDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 자동으로 스프링 bean으로 사용됨
public class MemberRepository {
    
    @PersistenceContext
    private EntityManager em;
    public void save(MemberDTO member){
        em.persist(member);
    }
    public MemberDTO findOne(Long id){
        return em.find(MemberDTO.class, id); // 해당 id로 member을 찾아줌
    }
    public List<MemberDTO> findAll(){
        List<MemberDTO> result = em.createQuery("select m from MemberDTO m", MemberDTO.class)
                .getResultList();
        return result;
    }
}
