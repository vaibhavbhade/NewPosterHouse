package com.swiftdroid.posterhouse.service;

import java.util.Set;

import com.swiftdroid.posterhouse.model.AuthenticationProvider;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.model.UserRole;
import com.swiftdroid.posterhouse.model.UserShipping;

public interface UserService {

	User findByUsername(String username);
	
    User findByEmail (String email);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	

	User createNewUserAfterSuccessHandler(String email, String name, AuthenticationProvider provider);

	void updateExistingUserAfterAuth2User(User user, String name, AuthenticationProvider google);

	void updateUserShipping(UserShipping userShipping, User user);
	
	public void setUserDefaultShipping(Long defaultShippingId, User user);

	User findById(Long id);
	
	}
