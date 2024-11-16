package com.showbook.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbook.Exception.InvalidLoginException;
import com.showbook.Exception.TheaterNotFoundException;
import com.showbook.Modals.Theaters;
import com.showbook.Repository.TheatersRepository;

@Service
public class TheatersService {

	@Autowired
	TheatersRepository theatersRepository;

	public Theaters addTheater(Theaters theaters) {
		theaters.setCreated_at(LocalDateTime.now());
		theaters.setUpdated_at(LocalDateTime.now());
		return theatersRepository.save(theaters);
	}
	
	public Theaters loginTheater(String email, String password) {

		Theaters theaters = theatersRepository.findByEmail(email);
		if (theaters != null && theaters.getPassword().equals(password)) {
			return theaters;
		} else {
			throw new InvalidLoginException("Invalid Email Or Password");
		}
	}

	public Theaters getTheaterById(Long theaderId) throws TheaterNotFoundException {
		Optional<Theaters> t1 = theatersRepository.getTheaterById(theaderId);

		if (t1.isPresent()) {
			return t1.get();
		}
		throw new TheaterNotFoundException("Theater does not exist with this id " + theaderId);
	}

	public Theaters updateTheater(Theaters theaters, Long theatersId) throws TheaterNotFoundException {
		Optional<Theaters> t1 = theatersRepository.findById(theatersId);

		if (t1.isEmpty()) {
			throw new TheaterNotFoundException("Theater does not exist with id " + theatersId);
		}

		Theaters oldTheater = t1.get();

		if (theaters.getTheater_name() != null) {
			oldTheater.setTheater_name(theaters.getTheater_name());
		}
		if (theaters.getLocation() != null) {
			oldTheater.setLocation(theaters.getLocation());
		}
		if (theaters.getTotal_screens() != null) {
			oldTheater.setTotal_screens(theaters.getTotal_screens());
		}
		if (theaters.getContact() != null) {
			oldTheater.setContact(theaters.getContact());
		}
		oldTheater.setUpdated_at(LocalDateTime.now());

		return theatersRepository.save(oldTheater);
	}
}
