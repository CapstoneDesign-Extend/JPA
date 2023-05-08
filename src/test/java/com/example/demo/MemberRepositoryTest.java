package com.example.demo;

import com.example.demo.model.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 기본적으로 rollback 함
public class MemberRepositoryTest {
    
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(value = false) // @Transactional 어노테이션이 rollback 하는 것을 막아줌
    public void testMember() throws Exception{
        // 전달
        MemberDTO member = new MemberDTO();
//        member.getName("memberA");

        // 진행
        Long saveId = memberRepository.save(member);
        MemberDTO findMember = memberRepository.find(saveId);

        // 결과
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member); // 같은 트랜잭션 안에 있어 동일함

    }
}