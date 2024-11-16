package com.showbook.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbook.Exception.MovieNotFoundException;
import com.showbook.Modals.Movies;
import com.showbook.Repository.MoviesRepository;

@Service
public class MoviesService {

	@Autowired
	MoviesRepository moviesRepository;

	public Movies addMovie(Movies movie) {
		return moviesRepository.save(movie);
	}

	public Movies getMoviesById(Long movieId) throws MovieNotFoundException {
		Optional<Movies> existMovie = moviesRepository.findMoviesById(movieId);
		if (existMovie.isPresent()) {
			return existMovie.get();
		}
		throw new MovieNotFoundException("Movie does't exist with this id " + movieId);
	}

	public Movies updateMovies(Movies movies, long movieId) throws MovieNotFoundException {
		Optional<Movies> existMovie = moviesRepository.findById(movieId);

		if (existMovie.isEmpty()) {
			throw new MovieNotFoundException("Movie does't exist with this id " + movieId);
		}

		Movies oldMovie = existMovie.get();

		if (movies.getTitle() != null) {
			oldMovie.setTitle(movies.getTitle());
		}
		if (movies.getGenre() != null) {
			oldMovie.setGenre(movies.getGenre());
		}
		if (movies.getLanguage() != null) {
			oldMovie.setLanguage(movies.getLanguage());
		}
		if (movies.getDuration() != null) {
			oldMovie.setDuration(movies.getDuration());
		}
		if (movies.getDescription() != null) {
			oldMovie.setDescription(movies.getDescription());
		}
		if (movies.getPoster() != null) {
			oldMovie.setPoster(movies.getPoster());
		}

		return moviesRepository.save(oldMovie);
	}
}
