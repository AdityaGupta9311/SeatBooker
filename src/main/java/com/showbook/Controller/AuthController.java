package com.showbook.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showbook.Modals.Theaters;
import com.showbook.Modals.Users;
import com.showbook.Repository.TheatersRepository;
import com.showbook.Repository.UsersRepository;
import com.showbook.Service.TheatersService;
import com.showbook.Service.UserService;
import com.showbook.config.JwtUtils;
import com.showbook.request.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TheatersService theatersService;
	
	@Autowired
	private TheatersRepository theatersRepository;

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
	public String loginUser(@RequestBody LoginRequest loginRequest) {
		
		if ("aditya.gupta@gmail.com".equals(loginRequest.getEmail()) && "1234".equals(loginRequest.getPassword())) {
			Map<String, Object> clamis = new HashMap<>();
			clamis.put("role", "ROLE_ADMIN");
			return JwtUtils.generateToken(clamis);
		}

		Users users = userService.loginUsers(loginRequest.getEmail(), loginRequest.getPassword());
		Map<String, Object> clamis = new HashMap<>();
		clamis.put("role", "ROLE_USER");
		return JwtUtils.generateToken(clamis);
	}
	
	
	@PostMapping("/theater")
	public ResponseEntity<String> addTheater(@RequestBody Theaters theaters) {
		Theaters isExistTheaterOnLocation = theatersRepository.findByLocation(theaters.getLocation());
		Theaters isExistTheaterwithEmail = theatersRepository.findByEmail(theaters.getEmail());
		if(isExistTheaterOnLocation != null) {
			return new ResponseEntity<String>("On This Location Theather Already Exist",HttpStatus.CONFLICT);
		}
		if(isExistTheaterwithEmail != null) {
			return new ResponseEntity<String>("This email is Already Exist",HttpStatus.CONFLICT);
		}
		theatersService.addTheater(theaters);
		return new ResponseEntity<>("Theater is Succesfully Added ", HttpStatus.CREATED);
	}
	
	@PostMapping("/logintheater")
	public String loginTheater(@RequestBody Theaters theaters) {

		Theaters t1 = theatersService.loginTheater(theaters.getEmail(), theaters.getPassword());
		Map<String, Object> clamis = new HashMap<>();
		clamis.put("role", "ROLE_THEATER");
		return jwtUtils.generateToken(clamis);

	}
	
	@PostMapping("/admin/register")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> createAdmin(@RequestBody Users admin) {

	    Users existingAdmin = usersRepository.findByEmail(admin.getEmail());
	    if (existingAdmin != null) {
	        return new ResponseEntity<>("This Email already exists", HttpStatus.CONFLICT);
	    }

	    admin.setRole("ROLE_ADMIN");
	    userService.registerUsers(admin);
	    return new ResponseEntity<>("Admin Successfully Registered", HttpStatus.CREATED);
	}
	
//	@PostMapping("/admin/login")
//	public String loginAdmin(@RequestBody LoginRequest loginRequest) {
//
//		Users users = userService.loginUsers(loginRequest.getEmail(), loginRequest.getPassword());
//		Map<String, Object> clamis = new HashMap<>();
//		clamis.put("role", "ROLE_ADMIN");
//		return JwtUtils.generateToken(clamis);
//	}

}
