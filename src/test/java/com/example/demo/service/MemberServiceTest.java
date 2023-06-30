package com.example.demo.service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class) // junit을 spring이랑 같이 엮어 실행하게 함
@SpringBootTest // spring 컨테이너 안에서 해당 코드들을 돌림
@Transactional // 데이터 변경은 트렌젝션 안에서 일어남 => 기본값으로 롤백함
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository MemberRepository;
    @Autowired EntityManager em; // 쿼리를 보고싶을 때

    @Test
//    @Rollback(value = false) // db에 잘 들어가나 확인하기 위해 rollback을 없애줌
    public void 회원가입() throws Exception{
        // given
        MemberDTO member = new MemberDTO();
        member.setName("재원");
        // when
        Long savaId = memberService.join(member);
        // then
        em.flush(); // 영속성 컨텍스트에 변경 등을 읽어 쿼리 알려줌
        assertEquals(member, MemberRepository.findOne(savaId));
    }
    @Test(expected = IllegalStateException.class) // try-catch문을 지울 수 있게 함
    public void 중복_회원_예외() throws Exception{
        // given
        MemberDTO member = new MemberDTO();
        member.setName("재원");
        MemberDTO member2 = new MemberDTO();
        member2.setName("재원");

        // when
        memberService.join(member);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 함");
    }
}