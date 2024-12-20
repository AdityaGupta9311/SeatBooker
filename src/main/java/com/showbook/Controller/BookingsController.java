package com.showbook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbook.Modals.Bookings;
import com.showbook.Service.BookingsService;

@RestController
public class BookingsController {

	@Autowired
	BookingsService bookingsService;

	@PostMapping("/makebooking")
	@Secured("ROLE_USER")
	public ResponseEntity<Bookings> makeBooking(@RequestBody Bookings bookings) {
		Bookings b1 = bookingsService.makeBooking(bookings);
		return new ResponseEntity<Bookings>(b1, HttpStatus.OK);
	}

	@GetMapping("/booking/{bookingId}")
	@Secured({"ROLE_THEATER","ROLE_ADMIN"})
	public ResponseEntity<Bookings> getBookingsById(@PathVariable Long bookingId) {
		Bookings b1 = bookingsService.getBookingById(bookingId);
		return new ResponseEntity<Bookings>(b1, HttpStatus.OK);
	}

	@GetMapping("/booking/user/{userId}")
	@Secured("ROLE_USER")
	public ResponseEntity<List<Bookings>> getBookingsByUserId(@PathVariable Long userId) {
		List<Bookings> b1 = bookingsService.getBookingsByUserId(userId);
		return new ResponseEntity<>(b1, HttpStatus.OK);
	}

	@DeleteMapping("/booking/cancel/{bookingId}")
	@Secured("ROLE_USER")
	public ResponseEntity<Bookings> cancelBookings(@PathVariable Long bookingId) {
		Bookings b1 = bookingsService.cancelBookings(bookingId);
		return new ResponseEntity<Bookings>(b1, HttpStatus.OK);
	}
}
