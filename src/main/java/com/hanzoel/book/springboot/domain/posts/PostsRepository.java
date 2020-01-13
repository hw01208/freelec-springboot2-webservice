package com.hanzoel.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* Dao. DB Layer 접근자. JPA에서는 리파지토리라고 부르며 인터페이스로 생성 */
/* <Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드 자동 생성 */
/* Entity 클래스와 기본 Entity Repository는 함께 위치해야 함 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
