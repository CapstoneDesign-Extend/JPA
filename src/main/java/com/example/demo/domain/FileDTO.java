//package com.example.demo.domain;
//
//import lombok.*;
//
//import javax.persistence.*;
//
///*
//    기본 생성자의 접근 제어를 PROTECTED로 설정하여
//    무분별한 객체 생성에 대해 한번 더 체크할 수 있게 함
// */
//@NoArgsConstructor(access = AccessLevel.PROTECTED) // 위 주석
//@Data // @Getter, @Setter 등등 기본적으로 필요한 어노테이션을 포함하고 있음
//@Entity
//@Table(name = "file")
//public class FileDTO {
//
//    @Id
//    @GeneratedValue // 자동 생성 => 시퀀스
//    @Column(name = "time_id")
//    private Long id;
//    @Column(nullable = false) // DDL 생성 시 not null이라는 조건을 줌
//    private String originFileName; // 확장자를 포함한 파일명 ex) test.jpg
//    @Column(nullable = false) // DDL 생성 시 not null이라는 조건을 줌
//    private String fullPath; // 저장될 경로와 파일명을 포함
//    public FileDTO toEntity() {
//        return FileDTO.builder()
//                .id(this.id)
//                .originFileName(this.originFileName)
//                .fullPath(this.fullPath)
//                .build();
//    }
//    @Builder
//    public FileDTO(Long id, String originFileName, String fullPath) {
//        // 모두 not null 이기에 생성자에 모두 대입해줌
//        this.id = id;
//        this.originFileName = originFileName;
//        this.fullPath = fullPath;
//    }
//}
