package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "time_schedule")
public class TimetableDTO { // 시간표
    @Id
    @GeneratedValue // 자동 생성 => 시퀀스
    @Column(name = "time_id")
    private Long id;
    @Column(name = "every_day")
    private String day; // 요일
    private LocalDateTime year; // 연도
    private int semester; // 학기 => 1학기 or 2학기
    private String schedule; // 일정 => 저장할 스케쥴
    @ManyToOne(fetch = FetchType.LAZY) // fetch=FetchType.LAZY : 지연 로딩으로 실시간 업로딩 되는 것을 막음
    @JoinColumn(name = "member_id")
    private MemberDTO member; // 한 명의 사용자는 여러 시간표를 가질 수 있음
}
