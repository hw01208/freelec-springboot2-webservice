package com.hanzoel.book.springboot.config.auth;

import com.hanzoel.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity          //  Spring Security 설정들을 활성화 시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // .csrf ~ : h2-console 화면을 사용하기 위해 해당 옵션들을 disable 함
                .and()
                    .authorizeRequests()            // URL별 권한 관리를 설정하는 옵션의 시작점. 이게 있어야 antMatcher 사용 가능
                    .antMatchers("/", "/css/**", "/images/**",
                                                "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // 권한 관리 대상 지정 옵션
                                                                                         // permitAll : 지정된 URL은 전체 열람 권한
                                                                                         // hasRole : USER 권한을 가진 사람만
                    .anyRequest().authenticated()   // 설정된 값들 이외 나머지 URL을 나타냄
                .and()                              // 여기서는 authenticated()를 추가하여 나머지 URL은 모두 인증된 사용자만
                    .logout()
                        .logoutSuccessUrl("/")      // 로그아웃 기능에 대한 여러 설정의 진입점. 성공시 "/" 주소로 이동
                .and()
                    .oauth2Login()              // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint()     // OAuth2 로그인 성공 이후 사용자 정보를 가져올 떄의 설정들을 담당
                            .userService(customOAuth2UserService);  // 소셜 로그인 성공 시 후속 조치를 진행할 인터페이스 구현체 등록
                            // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }
}
