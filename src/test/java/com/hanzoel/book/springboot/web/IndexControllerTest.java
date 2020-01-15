package com.hanzoel.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/* 실제로 URL 호출 시 페이지의 내용이 제대로 호출되는지에 대한 테스트 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void mainPage_loading() {

        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("Spring Boot Web Service Ver.2");    // 라는 문자열이 포함되어 있는지만 비교
    }
}
