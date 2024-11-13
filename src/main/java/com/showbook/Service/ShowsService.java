package com.showbook.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbook.Exception.MovieNotFoundException;
import com.showbook.Exception.ShowsNotFoundException;
import com.showbook.Exception.ShowtimeConflictException;
import com.showbook.Exception.TheaterNotFoundException;
import com.showbook.Modals.Movies;
import com.showbook.Modals.Shows;
import com.showbook.Modals.Theaters;
import com.showbook.Repository.MoviesRepository;
import com.showbook.Repository.ShowsRepository;
import com.showbook.Repository.TheatersRepository;

@Service
public class ShowsService {

	@Autowired
	MoviesRepository moviesRepository;

	@Autowired
	TheatersRepository theatersRepository;

	@Autowired
	ShowsRepository showsRepository;

	public Shows addShow(Shows shows) {

		Optional<Theaters> theaterOpt = theatersRepository.findById(shows.getTheater().getId());
		if (!theaterOpt.isPresent()) {
			throw new TheaterNotFoundException("Theater not found with this id " + shows.getTheater().getId());
		}

		Optional<Movies> moviesOpt = moviesRepository.findById(shows.getMovie().getId());
		if (!moviesOpt.isPresent()) {
			throw new MovieNotFoundException("Movies not found with this id " + shows.getMovie().getId());
		}

		Theaters t1 = theaterOpt.get();
		Movies m1 = moviesOpt.get();

		List<Shows> existingShows = showsRepository.findShowsByTheaterAndScreenNumber(shows.getTheater(),
				shows.getScreenNumber());

		for (Shows existingShow : existingShows) {
			if (existingShow.getShow_time().equals(shows.getShow_time())) {
				throw new ShowtimeConflictException(
						"Showtime conflict: There is already a show scheduled at this time.");
			}
		}

		Shows newShow = new Shows();
		newShow.setMovie(m1);
		newShow.setTheater(t1);
		newShow.setShow_time(shows.getShow_time());
		newShow.setAvailable_seats(shows.getAvailable_seats());
		newShow.setScreenNumber(shows.getScreenNumber());
		newShow.setAge_limit(shows.getAge_limit());
		newShow.setDuration(m1.getDuration());
		newShow.setPriceperseat(shows.getPriceperseat());
		newShow.setCreated_at(LocalDateTime.now());
		newShow.setUpdated_at(LocalDateTime.now());

		return showsRepository.save(newShow);
	}

	public List<Shows> getShowsByMoviesId(Long movieId) throws MovieNotFoundException {

		Optional<Movies> movieOpt = moviesRepository.findById(movieId);

		if (!movieOpt.isPresent()) {
			throw new MovieNotFoundException("Movie not found with this id " + movieId);
		}

		List<Shows> m1 = showsRepository.getShowByMovieId(movieId);
		return m1;
	}

	public List<Shows> getShowsByTheatersId(Long theatersId) throws TheaterNotFoundException {

		Optional<Theaters> movieOpt = theatersRepository.findById(theatersId);

		if (!movieOpt.isPresent()) {
			throw new TheaterNotFoundException("Theaters not found with this id " + theatersId);
		}

		List<Shows> t1 = showsRepository.getShowsByTheaterId(theatersId);
		return t1;
	}

	public Shows updateShow(Shows shows, long showId) throws ShowtimeConflictException {
		Optional<Shows> showOpt = showsRepository.findById(showId);

		if (showOpt.isEmpty()) {
			throw new ShowsNotFoundException("Show not exist with this id" + showId);
		}

		Shows oldShow = showOpt.get();

		if (shows.getShow_time() != null) {
			oldShow.setShow_time(shows.getShow_time());
		}
		if (shows.getAvailable_seats() >= 0) {
			oldShow.setAvailable_seats(shows.getAvailable_seats());
		}
		if (shows.getScreenNumber() >= 0) {
			oldShow.setScreenNumber(shows.getScreenNumber());
		}
		if (shows.getAge_limit() != null) {
			oldShow.setAge_limit(shows.getAge_limit());
		}
		if (shows.getPriceperseat() >= 0) {
			oldShow.setPriceperseat(shows.getPriceperseat());
			;
		}
		oldShow.setUpdated_at(LocalDateTime.now());

		return showsRepository.save(oldShow);
	}
}
