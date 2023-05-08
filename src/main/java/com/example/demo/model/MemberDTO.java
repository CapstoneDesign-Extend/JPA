package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class MemberDTO { // 회원 데이터 클래스
    @Id @GeneratedValue // @GeneratedValue : 자동 생성 => 시퀀스 값 같은
    @Column(name = "member_id")
    private Long id; // 임의로 사용할 키값
    @Column(name = "student_id")
    private Long studentId; // 학번
    private String name; // 회원 이름
    private Access access; // 주어질 권한
    private Login login; // 주어질 권한
    @OneToMany
    private List<BoardDTO> board = new ArrayList<>();
}
