package com.hanzoel.book.springboot.domain.posts;

import com.hanzoel.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter             // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타냄. 기본값으로 카멜케이스 → 언더스코어 네이밍
/* 실제로 DB의 테이블과 매칭될 Entity 클래스, 실제 쿼리를 날리기 보다는 이 클래스의 수정을 통해 작업 */
public class Posts extends BaseTimeEntity {
    @Id             // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK(기본키)의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼(굳이 선언 안해도 됨). 기본값 이외에 추가 변경이 필요한 옵션이 있을 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
