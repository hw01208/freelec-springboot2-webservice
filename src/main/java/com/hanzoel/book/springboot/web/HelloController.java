package com.hanzoel.book.springboot.web;

import com.hanzoel.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
public class HelloController {
    @GetMapping("/hello")   // HTTP 메소드인 Get의 요청을 받을 수 있는 API를 만들어 줌
    public String hello() {
        return "hello it's Spring boot";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,         // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
                                     @RequestParam("amount") int amount) {      // 외부(@RequestParam("name"))이란 이름으로 넘긴 파라미터를
        return new HelloResponseDto(name, amount);                              // String name에 저장하게 됨
    }
}
