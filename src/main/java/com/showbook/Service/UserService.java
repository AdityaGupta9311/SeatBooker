package com.showbook.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.showbook.Exception.InvalidLoginException;
import com.showbook.Exception.UserNotFoundException;
import com.showbook.Modals.Users;
import com.showbook.Repository.UsersRepository;

@Service
public class UserService {

	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	UsersRepository usersRepository;

	public Users registerUsers(Users users) {

		Users newUser = new Users();
		newUser.setId(users.getId());
		newUser.setName(users.getName());
		newUser.setEmail(users.getEmail());
		newUser.setRole("ROLE_USER");
		newUser.setPassword(passwordEncoder.encode(users.getPassword()));

		return usersRepository.save(newUser);
	}

	public Users loginUsers(String email, String password) {

		Users users = usersRepository.findByEmail(email);
		if (users != null && passwordEncoder.matches(password, users.getPassword())) {
			return users;
		} else {
			throw new InvalidLoginException("Invalid Email Or Password");
		}
	}

	public Users getUserById(Long userId) {
		Optional<Users> users = usersRepository.findById(userId);
		if (users.isPresent()) {
			return users.get();
		}
		throw new UserNotFoundException("User not exist with this Id " + userId);
	}

	public Users updateUser(Users users, long userId) throws UserNotFoundException {
		Optional<Users> u1 = usersRepository.findById(userId);

		if (u1.isEmpty()) {
			throw new UserNotFoundException("User not found with ID: " + userId);
		}

		Users oldUser = u1.get();

		if (users.getEmail() != null) {
			oldUser.setEmail(users.getEmail());
		}
		if (users.getName() != null) {
			oldUser.setName(users.getName());
		}
		return usersRepository.save(oldUser);
	}

}
