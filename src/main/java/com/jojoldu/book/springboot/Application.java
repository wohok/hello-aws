package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication  // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정, 항상 프로젝트의 최상단에 위치해야 함.
public class Application {  // 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS 실행
    }
}