package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "time_schedule")
public class TimetableDTO {
    @Id
    @GeneratedValue // 자동 생성 => 시퀀스
    @Column(name = "time_id")
    private int id;
    private String day; // 요일
    private String schedule; // 일정 => 저장할 스케쥴
    @OneToOne
    @JoinColumn(name = "member_id") // Member 테이블에 PK와 연결
    private MemberDTO member;
}
