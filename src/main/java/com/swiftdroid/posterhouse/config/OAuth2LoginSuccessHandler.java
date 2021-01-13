package com.swiftdroid.posterhouse.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.swiftdroid.posterhouse.model.AuthenticationProvider;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.service.UserService;
import com.swiftdroid.posterhouse.serviceimpl.UserSecurityService;


@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub

		CustomOAuth2User oAuth2User=(CustomOAuth2User) authentication.getPrincipal();
		
		String email=oAuth2User.getEmail();
		String name=oAuth2User.getName();
		
		System.out.println("Customer Email : "+email);
		
		User user=userService.findByEmail(email);
		
		if(user==null) {
			//register
			userService.createNewUserAfterSuccessHandler(email, name, AuthenticationProvider.GOOGLE);
		}
		else {
			//update existing user
			userService.updateExistingUserAfterAuth2User(user,name,AuthenticationProvider.GOOGLE);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
	
}
