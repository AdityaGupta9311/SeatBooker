package com.showbook.Controller;

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

import com.showbook.Modals.Users;
import com.showbook.Repository.UsersRepository;
import com.showbook.Service.UserService;
import com.showbook.request.LoginRequest;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UsersRepository usersRepository;

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody Users users) {

		Users isExistUser = usersRepository.findByEmail(users.getEmail());
		if (isExistUser != null) {
			return new ResponseEntity<>("This Email is already exist", HttpStatus.CONFLICT);
		}
		userService.registerUsers(users);
		return new ResponseEntity<>("User Successfully Added", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

//		try {
		Users users = userService.loginUsers(loginRequest.getEmail(), loginRequest.getPassword());
		return ResponseEntity.ok(users);
//		} catch (InvalidLoginException e) {
//			return new ResponseEntity<>("Invalid Email Or Password",HttpStatus.UNAUTHORIZED);
//		} catch (Exception e) {
//			return new ResponseEntity<>("An error occurred", HttpStatus.UNAUTHORIZED);
//		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = usersRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Users> getUserById(@PathVariable("userId") long id) {
		Users users = userService.getUserById(id);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/user/update/{userId}")
	public ResponseEntity<Users> updateUser(@RequestBody Users users, @PathVariable long userId) {
		Users updatedUser = userService.updateUser(users, userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable long userId) {
		if (usersRepository.existsById(userId)) {
			usersRepository.deleteById(userId);
			return new ResponseEntity<>("User deleted successfully " + userId, HttpStatus.OK);
		}
		return new ResponseEntity<>("User Not Found " + userId, HttpStatus.NOT_FOUND);
	}
}
