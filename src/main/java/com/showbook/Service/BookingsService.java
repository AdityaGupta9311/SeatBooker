package com.showbook.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbook.Exception.BookingNotFoundException;
import com.showbook.Exception.NotEnoughSeatsAvailableException;
import com.showbook.Exception.ShowsNotFoundException;
import com.showbook.Exception.UserBookingNotFoundExeption;
import com.showbook.Exception.UserNotFoundException;
import com.showbook.Modals.Bookings;
import com.showbook.Modals.Shows;
import com.showbook.Modals.Users;
import com.showbook.Repository.BookingsRepository;
import com.showbook.Repository.ShowsRepository;
import com.showbook.Repository.UsersRepository;

@Service
public class BookingsService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ShowsRepository showsRepository;

	@Autowired
	BookingsRepository bookingsRepository;

	public Bookings makeBooking(Bookings bookings) {

		if (bookings.getSeats() <= 0) {
			throw new IllegalArgumentException("Number of seats must be greater than zero.");
		}

		Optional<Users> userOpt = usersRepository.findById(bookings.getUser().getId());
		if (!userOpt.isPresent()) {
			throw new UserNotFoundException("User not found with id " + bookings.getUser().getId());
		}

		Optional<Shows> showOpt = showsRepository.findById(bookings.getShow().getId());
		if (!showOpt.isPresent()) {
			throw new ShowsNotFoundException("Show not found with id " + bookings.getShow().getId());
		}

		Users user = userOpt.get();
		Shows show = showOpt.get();

		if (show.getAvailable_seats() < bookings.getSeats()) {
			throw new NotEnoughSeatsAvailableException(
					"Not enough seats available for this show. Available : " + show.getAvailable_seats());
		}

		double pricePerSeat = show.getPriceperseat();
		int numberOfSeats = bookings.getSeats();
		double totalAmount = pricePerSeat * numberOfSeats;

		Bookings newBooking = new Bookings();
		newBooking.setUser(user);
		newBooking.setShow(show);
		newBooking.setPayment_status("Pending");
		newBooking.setTotal_amount(totalAmount);
		newBooking.setSeats(numberOfSeats);
		newBooking.setBooking_time(LocalDateTime.now());

		show.setAvailable_seats(show.getAvailable_seats() - bookings.getSeats());
		showsRepository.save(show);

		return bookingsRepository.save(newBooking);

	}

	public Bookings getBookingById(Long bookingId) throws BookingNotFoundException {
		Optional<Bookings> b1 = bookingsRepository.getBookingById(bookingId);
		if (b1.isPresent()) {
			return b1.get();
		}
		throw new BookingNotFoundException("Booking not Found with this id " + bookingId);
	}

	public List<Bookings> getBookingsByUserId(Long userId) throws UserBookingNotFoundExeption {
		Optional<Users> u1 = usersRepository.findById(userId);
		if (!u1.isPresent()) {
			throw new UserBookingNotFoundExeption("There are no booking with user Id " + userId);
		}
		List<Bookings> b1 = bookingsRepository.getBookingsByUserId(userId);
		return b1;
	}

	public Bookings cancelBookings(Long bookingId) throws BookingNotFoundException {
		Optional<Bookings> b1 = bookingsRepository.findById(bookingId);

		if (b1.isEmpty()) {
			throw new BookingNotFoundException("Booking not exist with this id " + bookingId);
		}

		Bookings bookings = b1.get();

		Shows shows = bookings.getShow();

		int seatToRelease = bookings.getSeats();
		shows.setAvailable_seats(shows.getAvailable_seats() + seatToRelease);
		showsRepository.save(shows);

		bookings.setPayment_status("Cancel");

		return bookingsRepository.save(bookings);

	}
}
