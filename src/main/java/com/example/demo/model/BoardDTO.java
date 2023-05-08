package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "border")
public class BoardDTO {
    @Id @GeneratedValue // 자동 생성 => 시퀀스
    @Column(name = "border_id")
    private int id;
    private String title; // 제목
    private String content; // 본문
    @ManyToOne
    @JoinColumn(name = "member_id") // 외래키 => 조인할 속성 이름
    private MemberDTO member; // 해당 멤버의 학번을 사용할 거임
    @Column(name = "view_count")
    private int viewcnt; // 조회수
    private Date finalDate; // 최종 등록된 날짜
}
