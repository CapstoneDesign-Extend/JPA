package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class MemberDTO { // 회원 클래스
    @Id @GeneratedValue // @GeneratedValue : 자동 생성 => 시퀀스 값 같은
    @Column(name = "member_id")
    private Long id; // 임의로 사용할 키값
    @Column(name = "student_id")
    private Long studentId; // 학번
    private String name; // 회원 이름
    @Enumerated(EnumType.STRING) // 데이터값을 int가 아닌 String으로 나오게 함
    private Access access; // 주어질 권한
    @Enumerated(EnumType.STRING) // 데이터값을 int가 아닌 String으로 나오게 함
    private Login login; // 주어질 권한
    @OneToMany(mappedBy = "member") // mappedBy : 연관관계 주인이 누구인지 상태 테이블 속성이름으로 명시해줌
    private List<BoardDTO> board = new ArrayList<>();
    @OneToOne(fetch=FetchType.LAZY) // fetch=FetchType.LAZY : 지연 로딩으로 실시간 업로딩 되는 것을 막음
    @JoinColumn(name = "time_id") // TimetableDTO 테이블에 PK와 연결
    private TimetableDTO timetable;
}
