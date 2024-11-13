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
import org.springframework.web.bind.annotation.RestController;

import com.showbook.Modals.Movies;
import com.showbook.Repository.MoviesRepository;
import com.showbook.Service.MoviesService;

@RestController
public class MoviesController {

	@Autowired
	MoviesService moviesService;

	@Autowired
	MoviesRepository moviesRepository;

	@PostMapping("/addmovie")
	public ResponseEntity<?> addMovies(@RequestBody Movies movies) {
		
//		Movies isExistMovie = moviesRepository.findByTitle(movies.getTitle());
//		if (isExistMovie != null) {
//			return new ResponseEntity<>("This Movie is already exist", HttpStatus.CONFLICT);
//		}
		
		Movies newMovie = moviesService.addMovie(movies);
		return new ResponseEntity<Movies>(newMovie, HttpStatus.CREATED);
	}

	@GetMapping("/gettallmovies")
	public ResponseEntity<List<Movies>> getAllMovies() {
		List<Movies> m1 = moviesRepository.findAll();
		return new ResponseEntity<List<Movies>>(m1, HttpStatus.OK);
	}

	@GetMapping("/getmovie/{movieId}")
	public ResponseEntity<Movies> getMoviesById(@PathVariable long movieId) {
		Movies m1 = moviesService.getMoviesById(movieId);
		return new ResponseEntity<>(m1, HttpStatus.OK);
	}

	@PostMapping("/movie/updatemovie/{movieId}")
	public ResponseEntity<Movies> updateMovie(@RequestBody Movies movies, @PathVariable long movieId) {
		Movies m1 = moviesService.updateMovies(movies, movieId);
		return new ResponseEntity<Movies>(m1, HttpStatus.OK);
	}

	@DeleteMapping("/movie/delete/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable long movieId) {
		if (moviesRepository.existsById(movieId)) {
			moviesRepository.deleteById(movieId);
			return new ResponseEntity<String>("Movie Deleted By this Id " + movieId, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie Does Not Exist By this Id " + movieId, HttpStatus.NOT_FOUND);
	}
}
