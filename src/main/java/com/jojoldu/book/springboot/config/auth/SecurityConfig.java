package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
                .and().authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점.
                    // 권한 관리 대상을 지정하는 옵션
                    .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/**")
                    // "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 주었음.
                    .permitAll()
                    // POST 메소드이면서 "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL들은 모두 인증된 사용자들에게만 허용(로그인)
                .and().logout().logoutSuccessUrl("/") // 로그아웃 시 /주소로 이동
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService); // 로그인 성공 시 사용자 정보를 설정 customOAuth2UserService에
    }
}
