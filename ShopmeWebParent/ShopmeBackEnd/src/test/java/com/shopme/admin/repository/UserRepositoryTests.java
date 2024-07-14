package com.shopme.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.admin.repository.UserRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		
		Role role = entityManager.find(Role.class, 1);
		User user = new User("test@gmail.com", "test@1234", "Piyush", "Jaiswal");
		user.addRole(role);
		
		User savedUser = userRepository.save(user);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		
		User user = new User("user@gmail.com", "user@1234", "Shivam", "Jaiswal");
		Role roleEditior = new Role(3);
		Role roleAssistant = new Role(5);
		
		user.addRole(roleEditior);
		user.addRole(roleAssistant);
		
		User savedUser = userRepository.save(user);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		
		Iterable<User> userList = userRepository.findAll();
		userList.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testUserById() {
		User user = userRepository.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User user = userRepository.findById(1).get();
		user.setEnabled(true);
		user.setEmail("jaiswal.piyush987@gmail.com");
		userRepository.save(user);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user = userRepository.findById(2).get();
		Role roleAssistant = new Role(5);
		user.getRoles().remove(roleAssistant);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		userRepository.deleteById(userId);
	}
}
