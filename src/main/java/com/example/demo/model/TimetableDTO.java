package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "time_schedule")
public class TimetableDTO {
    @Id
    @GeneratedValue // 자동 생성 => 시퀀스
    @Column(name = "time_id")
    private int id;
    @Column(name = "every_day")
    private String day; // 요일
    private String schedule; // 일정 => 저장할 스케쥴
    @OneToOne(fetch= LAZY, mappedBy = "timetable") // fetch=FetchType.LAZY : 지연 로딩으로 실시간 업로딩 되는 것을 막음
    private MemberDTO member;
}
