package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성될 위치를 지정 -> 파라미터로 지정했으니 메소드의 파라미터로 선언된 객체에만 사용할 수 있다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // 이 파일을 어노테이션 클래스로 지정한다.
}
