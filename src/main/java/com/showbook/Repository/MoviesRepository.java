package com.showbook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showbook.Modals.Movies;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {

	public Optional<Movies> findMoviesById(Long movieId);
	
	public Movies findByTitle(String title);
}
