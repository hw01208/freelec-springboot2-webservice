package com.hanzoel.book.springboot.web;

import com.hanzoel.book.springboot.config.auth.dto.SessionUser;
import com.hanzoel.book.springboot.domain.user.User;
import com.hanzoel.book.springboot.service.posts.PostsService;
import com.hanzoel.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {  // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 할 수 있음
                                        // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
                // CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser 저장

        if(user != null) {      // 세션에 저장된 값이 있을 때만 model에 userName으로 등록함. 없으면 로그인 버튼 보임
            model.addAttribute("userName", user.getName());
        }

        return "index";                 // 머스테치 스타터 덕에 문자열을 반환할 때 경로, 확장자 자동 지정
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";        // /posts/save 호출 시 posts-save.mustache 호출하는 메소드
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
