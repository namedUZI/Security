package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); // #3 不验证该路径下的请求，
	}*/
	/* 权限*/
	@Autowired
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");
	}
/* 路径*/
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
					/*.permitAll();*/
				.and()
			.httpBasic()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // 开启csrf的代码
		//http.csrf().disable();
	}
}
