package com.shopme.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateFirstRole() {
		
		Role roleAdmin = new Role("Admin", "Manage everything");
		Role savedRole = roleRepository.save(roleAdmin);
	
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		
		Role roleSalesPerson = new Role("Salesperson", "Manage product price, customer, shipping, order and sales report");
		
		Role roleEditor = new Role("Editor", "Manage categories, brands, products, articles and menus");
		
		Role roleShiper = new Role("Shiper", "View products, view orders and update order status");
		
		Role roleAssistant = new Role("Assistant", "Manage question and reviews");
		
		roleRepository.saveAll(List.of(roleSalesPerson, roleEditor, roleShiper, roleAssistant));
	}
}
