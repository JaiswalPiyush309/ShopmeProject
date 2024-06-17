package com.shopme.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopme.common.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
}
