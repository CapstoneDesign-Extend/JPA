package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Timetable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit4을 spring이랑 같이 엮어 실행하게 함
@SpringBootTest // spring 컨테이너 안에서 해당 코드들을 돌림
@Transactional // 데이터 변경은 트렌젝션 안에서 일어남 => 기본값으로 롤백함
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    EntityManager em; // 쿼리를 보고싶을 때

    @Test
    public void 스케쥴_확인(){
        // Given
        Member member = new Member();
        member.setName("John Doe");
        memberRepository.save(member);

        Timetable timetable1 = new Timetable();
        timetable1.setDay("월요일");
        timetable1.setSchedule_year(2023);
        timetable1.setSemester(1);
        timetable1.setSchedule("국어 수업");
        timetable1.setMember(member); // member 객체 설정
        member.addTimetable(timetable1);

        Timetable timetable2 = new Timetable();
        timetable2.setDay("화요일");
        timetable2.setSchedule_year(2023);
        timetable2.setSemester(1);
        timetable2.setSchedule("수학 수업");
        timetable2.setMember(member); // member 객체 설정
        member.addTimetable(timetable2);


        // When
//        memberRepository.save(member);
        em.persist(member);

        // Then
        Member savedMember = memberRepository.findOne(member.getId());
        assertNotNull(savedMember);
        assertEquals(2, savedMember.getTimetables().size());
    }
}