package com.kdigital.spring7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 		// 이 파일이 설정 파일임을 나타내는 annotation
@EnableWebSecurity // 웹 보안은 모두 이 설정 파일을 따른다.
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1) 웹 요청에 대한 접근 권한 설정
		http
			.authorizeHttpRequests((auth)-> auth
					.requestMatchers(
							"/"
							, "/board/boardList"
							, "/board/boardDetail"
							, "/user/join"
							, "/login"
							, "/reply/replyAll"
							, "/images/**"
							, "/css/**"
							, "/script/**").permitAll() // permitAll() : 인증 절차 없이도 접근 가능한 요청 정보
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
					.anyRequest().authenticated()	// 기타 다른 경로는 인증된 사용자만 접근 가능(가장 마지막에 와야 함)
					);
		// Custom Login 설정
		http
			.formLogin((auth) -> auth
			.loginPage("/user/login")
			.usernameParameter("userId")
			.passwordParameter("userPwd")
			.loginProcessingUrl("/user/loginProc") 
			.defaultSuccessUrl("/").permitAll() // 로그인 성공했을 때 갈 url
			);
		
		// 로그아웃 설정
		http
			.logout((auth) -> auth
					.logoutUrl("/user/logout") // 로그아웃 처리 URL
					.logoutSuccessUrl("/")		// 로그아웃 성공시 URL
					.invalidateHttpSession(true) // 세션 무효화
					); 
					
		// POST 요청시 CSRF (Cross-Site Request Forgery) 비활성화(개발시)
		http
			.csrf((auth) -> auth.disable());
		
		return http.build();
		
	}
		// 비밀번호 암호화
		@Bean
		BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
	
}
