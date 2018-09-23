package com.lin.microservice.service.user.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lin.common.core.ControllerSupport;
import com.lin.common.core.ResourceNotFoundException;
import com.lin.microservice.service.user.entity.User;
import com.lin.microservice.service.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends ControllerSupport {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {
		User user = userService.findById(id);

		if (user == null)
			throw new ResourceNotFoundException("User not found by id: " + id);

		return user;
	}

	@GetMapping("/username/{userName}")
	public User getUser(@PathVariable String userName) {
		User user = userService.findByUsername(userName);

		if (user == null)
			throw new ResourceNotFoundException("User not found by username: " + userName);

		return user;
	}

	@PostMapping("")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		try {
			userService.save(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());			
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

		User entity = userService.findById(id);

		if (entity == null)
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		try {
			userService.save(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());			
		}

		return ResponseEntity.noContent().build();
	}	

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.delete(id);
	}	
	
}
