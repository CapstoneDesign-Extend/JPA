package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "file_table")
public class FileDTO {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id; // 개별 아이디

    private String fileName; // 파일 이름

    private long fileSize; // 파일 사이즈

    private String fileType; // 파일 타입

    @Lob // 대용량 데이터 매핑 시 필요 ex) BOLB 형식
    private byte[] fileData; // 파일에 대한 데이터로 파일의 내용을 바이트 배열로 변환한 데이터가 저장됨

    // Getter, Setter, 생성자 등을 구현합니다.
}