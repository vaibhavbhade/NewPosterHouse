package com.swiftdroid.posterhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import com.swiftdroid.posterhouse.serviceimpl.CustomerOAuth2UserService;
import com.swiftdroid.posterhouse.serviceimpl.UserSecurityService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
@Autowired
private Environment env;
	
@Autowired
private UserSecurityService userSecurityService;

@Autowired
private CustomerOAuth2UserService oAuth2UserService;

@Autowired
private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


@Bean
public BCryptPasswordEncoder passEncoder() {
 BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
return encoder;
 
}
 
 public static final String[] PUBLIC_MATCHERS= {
		 "/css/**",
		 "/js/**",
		 "/img/**",
		 "/downloadImage",
		 "/shoppingCart","/img/i1.jpg",
"/fetchProductBysizeandproductId",
		 "/","/productDetail",
		 "/registration",
		 "/fetchProduct","/fetchProductCategoryWise",
		 "/forgetPassword","/newUser",
		 "/login","/oauth2/**",
		 "/fonts/**","/searchProduct"
		
 };
	


@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	
	
	http.csrf().disable().cors().disable().formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
	.loginPage("/login").permitAll().and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(oAuth2UserService).and().successHandler(oAuth2LoginSuccessHandler)
	
	.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll().and().rememberMe();
	http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
	}

@Autowired
private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	auth.userDetailsService(userSecurityService).passwordEncoder(passEncoder());

}
@Bean
public RestTemplate getRestTemplate() {
   return new RestTemplate();
}
}
