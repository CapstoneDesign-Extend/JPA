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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 데이터 변경은 트렌젝션 안에서 일어남
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository MemberRepository;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception{
        // given
        MemberDTO member = new MemberDTO();
        member.setName("재원");
        // when
        Long savaId = memberService.join(member);
        // then
        assertEquals(member, MemberRepository.findOne(savaId));
    }
    @Test
    public void 중복_회원_예외() throws Exception{
        // given

        // when

        // then
    }
}