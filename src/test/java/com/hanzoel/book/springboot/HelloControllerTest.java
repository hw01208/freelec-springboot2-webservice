package com.hanzoel.book.springboot;

import com.hanzoel.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class) // 테스트 진행시 JUnit에 내장된 실행자 외에 다른 실행자(SpringRunner)를 실행시킴
                             // 스프링부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest // Web에 집중할 수 있는 어노테이션. @Controller 등 사용 가능
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 Bean을 주입받음
    private MockMvc mvc; // 웹 API 테스트시 사용, 스프링 MVC 테스트의 시작점, 이 클래스를 통하여 HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello it's Spring boot";

        mvc.perform(get("/hello"))               // MockMvc를 통해 /hello 주소로 HTTP GET 요청, 여러 검증 이어서 선언 가능
                    .andExpect(status().isOk())             // mvc.perform의 결과 검증, HTTP Header의 status 검증, 200/404/500 등 검증
                    .andExpect(content().string(hello));    // mvc.perform의 결과 검증, 응답 본문의 내용 검증, Controller에서 hello를 리턴하기 때문에 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)        // API 테스트시 사용될 요청 파라미터 설정. 단 String만 허용됨.
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))        // JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));   // $를 기준으로 필드명을 명시
    }
}
