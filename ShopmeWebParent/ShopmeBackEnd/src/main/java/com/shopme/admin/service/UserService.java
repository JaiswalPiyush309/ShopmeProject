package com.shopme.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.admin.repository.RoleRepository;
import com.shopme.admin.repository.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> allUserList() {
		return (List<User>) userRepository.findAll();
	}
	
	public List<Role> roleList() {
		return this.roleRepository.findAll();
	}
	
	public void save(User user) {
		this.userRepository.save(user);
	}
}
