package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "border")
public class BoardDTO { // 게시판 클래스
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
    private LocalDateTime finalDate; // 최종 등록된 날짜
    @Enumerated(EnumType.STRING) // 데이터값을 int가 아닌 String으로 나오게 함
    private Kind kind; // 게시판 종류
    @OneToMany(mappedBy = "board") // mappedBy : 연관관계 주인이 누구인지 상태 테이블 속성이름으로 명시해줌
    private List<CommentDTO> comments = new ArrayList<>();
}
