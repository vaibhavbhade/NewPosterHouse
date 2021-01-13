package com.swiftdroid.posterhouse.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
}