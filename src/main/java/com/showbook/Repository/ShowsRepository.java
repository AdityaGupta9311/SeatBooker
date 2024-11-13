package com.showbook.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showbook.Modals.Shows;
import com.showbook.Modals.Theaters;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Long> {
	
    List<Shows> findShowsByTheaterAndScreenNumber(Theaters theater, int screenNumber);
    
    List<Shows> getShowByMovieId(Long movieId);
    
    List<Shows> getShowsByTheaterId(Long theater_Id);
}
