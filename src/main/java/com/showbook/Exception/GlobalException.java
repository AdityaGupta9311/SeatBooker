package com.showbook.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherEceptionHandler(Exception ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<ErrorDetails> InvalidLoginEceptionHandler(InvalidLoginException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> UserNotFoundEceptionHandler(UserNotFoundException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TheaterNotFoundException.class)
	public ResponseEntity<ErrorDetails> TheaterNotFoundEceptionHandler(TheaterNotFoundException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ErrorDetails> MovieNotFoundEceptionHandler(MovieNotFoundException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ShowtimeConflictException.class)
	public ResponseEntity<ErrorDetails> ShowTimeConflictNotFoundEceptionHandler(ShowtimeConflictException ue,
			WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ShowsNotFoundException.class)
	public ResponseEntity<ErrorDetails> ShowNotFoundEceptionHandler(ShowsNotFoundException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotEnoughSeatsAvailableException.class)
	public ResponseEntity<ErrorDetails> NotEnoughSeatsAvailableExceptionHandler(NotEnoughSeatsAvailableException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ErrorDetails> BookingNotFoundExceptionHandler(BookingNotFoundException ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserBookingNotFoundExeption.class)
	public ResponseEntity<ErrorDetails> UserBookingNotFoundExeptionHandler(UserBookingNotFoundExeption ue, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

}
