package com.showbook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showbook.Modals.Shows;
import com.showbook.Repository.BookingsRepository;
import com.showbook.Repository.ShowsRepository;
import com.showbook.Service.ShowsService;

@RestController
public class ShowsController {

	@Autowired
	ShowsService showsService;

	@Autowired
	ShowsRepository showsRepository;
	
	@Autowired
	BookingsRepository bookingsRepository;

	@PostMapping("/addshows")
	public ResponseEntity<Shows> addShows(@RequestBody Shows shows) {
		Shows s1 = showsService.addShow(shows);
		return new ResponseEntity<Shows>(s1, HttpStatus.CREATED);
	}

	@GetMapping("/shows/movie/{movieId}")
	public ResponseEntity<List<Shows>> getShowsByMovieId(@PathVariable Long movieId) {
		List<Shows> m1 = showsService.getShowsByMoviesId(movieId);
		return new ResponseEntity<List<Shows>>(m1, HttpStatus.OK);
	}

	@GetMapping("/shows/theater/{theatersId}")
	public ResponseEntity<List<Shows>> getShowsBytheaterId(@PathVariable Long theatersId) {
		List<Shows> t1 = showsService.getShowsByTheatersId(theatersId);
		return new ResponseEntity<List<Shows>>(t1, HttpStatus.OK);
	}

	@PostMapping("/show/update/{showId}")
	public ResponseEntity<Shows> updateShows(@RequestBody Shows shows, @PathVariable Long showId) {
		Shows s1 = showsService.updateShow(shows, showId);
		return new ResponseEntity<Shows>(s1, HttpStatus.OK);
	}

	@DeleteMapping("/show/delete/{showId}")
	public ResponseEntity<?> deleteShows(@PathVariable Long showId) {
		if (!showsRepository.existsById(showId)) {
	        return new ResponseEntity<>("Show not found with this id " + showId, HttpStatus.NOT_FOUND);
	    }

	    if (bookingsRepository.existsByShowId(showId)) {
	        return new ResponseEntity<>("Cannot delete show with id " + showId + " because there are related bookings.", HttpStatus.CONFLICT);
	    }

	    showsRepository.deleteById(showId);
	    return new ResponseEntity<>("Show is deleted successfully with this id " + showId, HttpStatus.OK);
	}
}
