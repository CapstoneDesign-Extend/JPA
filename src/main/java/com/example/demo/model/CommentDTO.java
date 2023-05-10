package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter @Setter
public class CommentDTO { // 댓글 클래스
    @Id
    @GeneratedValue // 자동 생성 => 시퀀스
    @Column(name = "comment_id")
    private int id;
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL) // cascade = CascadeType.ALL : Comment 테이블을 persist 할 때 Border 테이블도 같이 해줌
    @JoinColumn(name = "border_id") // 게시판 테이블에 PK와 연결해줌
    private BoardDTO board; // 게시판 id를 가져오기 위해
    private String content; // 본문
    private LocalDateTime finalDate; // 최종 등록된 날짜
    @Column(name = "click_count")
    private int count; // 좋아요 갯수

    //== 연관관계 메소드 ==//
    public void setBoard(BoardDTO board){
        this.board = board;
        board.getComments().add(this);
    }
}
