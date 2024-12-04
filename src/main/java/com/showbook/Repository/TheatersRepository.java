package com.showbook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showbook.Modals.Theaters;

@Repository
public interface TheatersRepository extends JpaRepository<Theaters, Long> {

	public Optional<Theaters> getTheaterById(long theaterId);

	public Theaters findByEmail(String email);
	
	public Theaters findByLocation(String location);
}
