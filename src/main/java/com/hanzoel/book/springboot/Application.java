package com.hanzoel.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
/* 스프링 부트 자동 설정, 스프링 빈 읽기와 생성 모두 자동 설정 */
public class Application {
    public static void main(String[] args) {
        // 내장 WAS 실행 : 항상 서버에 톰캣을 설치할 필요가 없고 스프링 부트로 만들어진 Jar 파일(실행 가능한 Java 패키징파일)로 실행하면 됨
        SpringApplication.run(Application.class, args);
    }
}
