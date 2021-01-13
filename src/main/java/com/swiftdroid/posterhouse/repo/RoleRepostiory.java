package com.swiftdroid.posterhouse.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.Role;



public interface RoleRepostiory extends CrudRepository<Role, Long>{
Role findByname(String name);

}
