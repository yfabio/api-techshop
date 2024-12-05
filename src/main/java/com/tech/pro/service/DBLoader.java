package com.tech.pro.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.pro.model.Product;
import com.tech.pro.model.User;
import com.tech.pro.repository.OrderRepository;
import com.tech.pro.repository.ProductRepository;
import com.tech.pro.repository.UserRepository;

@Component
public class DBLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBLoader.class);

	@Value("classpath:temp/products.json")
	private Resource resource;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Product> getProductsFromDummyFile() {
		try {
			Product[] products = objectMapper.readValue(resource.getInputStream(), Product[].class);
			return Arrays.stream(products).toList();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("unable to read dummy file");
		}
	}

	public List<User> createUsers() {

		List<User> list = new ArrayList<>();

		User u1 = new User();
		u1.setName("Admin");
		u1.setEmail("admin@email.com");
		u1.setPassword("123");
		u1.setIsAdmin(Boolean.TRUE);

		list.add(u1);

		User u2 = new User();
		u2.setName("John Doe");
		u2.setEmail("john@email.com");
		u2.setPassword("123");
		u2.setIsAdmin(Boolean.FALSE);
		list.add(u2);

		User u3 = new User();
		u3.setName("Jane Doe");
		u3.setEmail("jane@email.com");
		u3.setPassword("123");
		u3.setIsAdmin(Boolean.FALSE);
		list.add(u3);

		return list;

	}

	public void importData() {
		try {
			orderRepository.deleteAll();
			productRepository.deleteAll();
			userRepository.deleteAll();

			List<User> insertedUsers = createUsers().stream().map(user -> {
				String password = user.getPassword();
				user.setPassword(passwordEncoder.encode(password));
				return userRepository.save(user);
			}).toList();

			User adminUser = insertedUsers.get(0);

			getProductsFromDummyFile().stream().map(p -> {
				p.setUser(adminUser);
				return productRepository.insert(p);
			}).toList();

			LOGGER.info("data has been imported");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroyData() {
		try {
			orderRepository.deleteAll();
			productRepository.deleteAll();
			userRepository.deleteAll();
			LOGGER.info("data has been deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
