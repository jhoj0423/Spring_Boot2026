package com.green;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

// 지금 AppConfig클래스는 환경 설정해주는 클래스야
// 여기서 객체 생성해
@Configurable
public class AppConfig {
	
	//MemberService를 Bean객체로 생성하시오.
//	@Bean
//	public MemberService memberservice() {
//		return new MemberService();
//	}
}
