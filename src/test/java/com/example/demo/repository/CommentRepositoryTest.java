package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    EntityManager em;

    @org.junit.Test
    public void saveCommentTest() {
        // Given
        Board board = new Board();
        board.setId(1L);
        Member member = new Member();
        member.setId(1L);
        Comment comment = new Comment();
        comment.setContent("새로운 댓글 내용");

        // When
        comment.setBoard(board);
        comment.setMember(member);
        commentRepository.saveComment(board.getId(), member.getId(), comment.getContent());

        // Then
        Comment savedComment = em.find(Comment.class, comment.getId());
        assertNotNull(savedComment);
        assertEquals(board.getId(), savedComment.getBoard().getId());
        assertEquals(member.getId(), savedComment.getMember().getId());
        assertEquals(comment.getContent(), savedComment.getContent());
    }

    @org.junit.Test
    public void findByMemberTest() {
        // Given
        Long memberId = 1L;

        // When
        List<Comment> comments = commentRepository.findByMember(new Member(memberId));

        // Then
        assertEquals(2, comments.size());
        for (Comment comment : comments) {
            assertEquals(memberId, comment.getMember().getId());
        }
    }

    @Test
    public void findByContentContainingTest() {
        // Given
        String content = "댓글";

        // When
        List<Comment> comments = commentRepository.findByContentContaining(content);

        // Then
        assertEquals(2, comments.size());
        for (Comment comment : comments) {
            assertTrue(comment.getContent().contains(content));
        }
    }

    @Test
    public void deleteByIdTest() {
        // Given
        Long commentId = 1L;

        // When
        commentRepository.deleteById(commentId);

        // Then
        em.getTransaction().begin();
        Comment deletedComment = em.find(Comment.class, commentId);
        em.getTransaction().commit();

        assertNull(deletedComment);
    }

    @Test
    public void deleteByMemberTest() {
        // Given
        Long memberId = 1L;

        // When
        commentRepository.deleteByMember(new Member(memberId));

        // Then
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT c FROM Comment c WHERE c.member.id = :memberId");
        query.setParameter("memberId", memberId);
        List<Comment> deletedComments = query.getResultList();
        em.getTransaction().commit();

        assertEquals(0, deletedComments.size());
    }
}