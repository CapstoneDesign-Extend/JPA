package com.example.demo.repository;

import com.example.demo.domain.FileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 자동으로 스프링 bean으로 사용됨
@RequiredArgsConstructor
public class FileRepository { // CRUD를 수행하는 클래스

    @PersistenceContext
    private final EntityManager em;

    //-- 파일 저장 --//
    public FileDTO save(FileDTO file) {
        // 파일 엔티티를 영속화하여 데이터베이스에 저장
        em.persist(file);
        return file;
    }

    //-- 파일 찾기 --//
    public FileDTO findById(Long id) {
        // 주어진 ID로 파일 엔티티 조회
        return em.find(FileDTO.class, id);
    }

    //-- 파일 삭제 --//
    public void deleteById(Long id) {
        // 주어진 ID로 파일 엔티티 조회하여 삭제
        FileDTO file = findById(id);
        if (file != null) {
            em.remove(file);
        }
    }

    /*
    //-- 파일 전체 조회 후 리스트 --//
    public List<FileDTO> findAll() {
        // 모든 파일 엔티티를 조회하여 리스트로 반환
        return em.createQuery("SELECT f FROM FileDTO f", FileDTO.class)
                .getResultList();
    }
    */

    // 기타 필요한 메소드들 작성

}
