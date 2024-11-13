package com.showbook.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showbook.Modals.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

	public Optional<Bookings> getBookingById(Long bookingId);
	
	public List<Bookings> getBookingsByUserId(Long userId);

	public boolean existsByShowId(Long showId);
}
