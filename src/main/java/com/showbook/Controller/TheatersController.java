package com.showbook.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbook.Modals.Theaters;
import com.showbook.Repository.TheatersRepository;
import com.showbook.Service.TheatersService;

@RestController
public class TheatersController {

	@Autowired
	TheatersService theatersService;

	@Autowired
	TheatersRepository theatersRepository;

	@PostMapping("/theater")
	public ResponseEntity<String> addTheater(@RequestBody Theaters theaters) {
		theatersService.addTheater(theaters);
		return new ResponseEntity<>("Theater is Succesfully Added ", HttpStatus.CREATED);
	}

	@GetMapping("/getalltheaters")
	public ResponseEntity<List<Theaters>> getAllTheaters() {
		List<Theaters> t1 = theatersRepository.findAll();
		return new ResponseEntity<>(t1, HttpStatus.OK);
	}

	@GetMapping("/getheaterById/{theaterId}")
	public ResponseEntity<Theaters> geTheaterById(@PathVariable Long theaterId) {
		Theaters t1 = theatersService.getTheaterById(theaterId);
		return new ResponseEntity<Theaters>(t1, HttpStatus.OK);
	}

	@PostMapping("/theater/update/{theaterId}")
	public ResponseEntity<String> updateTheater(@RequestBody Theaters theaters, @PathVariable Long theaterId)
			throws Exception {
		Theaters t1 = theatersService.updateTheater(theaters, theaterId);
		return new ResponseEntity<>("Theater is Updated Successfully " + t1, HttpStatus.OK);
	}

	@DeleteMapping("/theater/delete/{theaterId}")
	public ResponseEntity<String> deleteTheater(@PathVariable Long theaterId) {
		Optional<Theaters> t1 = theatersRepository.findById(theaterId);

		if (t1.isPresent()) {
			theatersRepository.deleteById(theaterId);
			return new ResponseEntity<>("Theater Deleted with this id " + theaterId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Theater does not exixts with this id " + theaterId, HttpStatus.NOT_FOUND);
		}
	}
}
