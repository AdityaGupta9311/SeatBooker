package com.showbook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbook.Modals.Users;
import com.showbook.Repository.UsersRepository;
import com.showbook.Service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UsersRepository usersRepository;


	@GetMapping("/admin/user/users")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = usersRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Users> getUserById(@PathVariable("userId") long id) {
		Users users = userService.getUserById(id);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/user/update/{userId}")
	@Secured("ROLE_USER")
	public ResponseEntity<Users> updateUser(@RequestBody Users users, @PathVariable long userId) {
		Users updatedUser = userService.updateUser(users, userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/user/delete/{userId}")
	@Secured("ROLE_USER")
	public ResponseEntity<String> deleteUser(@PathVariable long userId) {
		if (usersRepository.existsById(userId)) {
			usersRepository.deleteById(userId);
			return new ResponseEntity<>("User deleted successfully " + userId, HttpStatus.OK);
		}
		return new ResponseEntity<>("User Not Found " + userId, HttpStatus.NOT_FOUND);
	}
}
