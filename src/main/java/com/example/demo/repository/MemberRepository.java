package com.example.demo.repository;


import com.example.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

//@Slf4j
@Repository // 자동으로 스프링 bean으로 사용됨
@RequiredArgsConstructor
public class MemberRepository { // repository 패키지는 DB에 접근하는 모든 코드가 모여있음

    @PersistenceContext // EntityManager를 주입받기 위해 사용
    private final EntityManager em;


    public void save(Member member){ //-- 멤버 저장 --//
        em.persist(member);
    }
    public Member findOne(Long id){ //-- 해당 id로 member을 찾아줌 --//
        return em.find(Member.class, id);
    }
    public List<Member> findAll(){ //-- 저장된 회원을 리스트 형식으로 찾음 --//
        // JPA는 객체를 대상으로 쿼리문을 작성 => 메소드 인자 중 두 번째 인자가 타입을 나타냄
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
    public void delete(Member member) { //-- 해당 멤버 삭제 --//
        em.remove(member);
    }
    public List<Member> findByStudentId(int studentId){ // 학번으로 회원을 찾음
        return em.createQuery("select m from Member m where m.studentId=:studentId", Member.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }
    public Optional<Member> findByLoginId(String loginId) { //-- logId 필드로 찾고 해당 결과 반환 --//

        return findAll().stream()
                .filter(m -> m.getLongId().equals(loginId))
                .findFirst();
    }


}
