//package com.showbook.Exception;
//
//public class Test {
//
//	
//	
//	import org.springframework.http.HttpStatus;
//	import org.springframework.http.ResponseEntity;
//	import org.springframework.web.bind.annotation.ControllerAdvice;
//	import org.springframework.web.bind.annotation.ExceptionHandler;
//
//	@ControllerAdvice
//	public class GlobalExceptionHandler {
//
//	    @ExceptionHandler(InvalidLoginException.class)
//	    public ResponseEntity<String> handleInvalidLogin(InvalidLoginException e) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//	    }
//
//	    @ExceptionHandler(UserNotFoundException.class)
//	    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//	    }
//
//	    @ExceptionHandler(EmailAlreadyExistsException.class)
//	    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException e) {
//	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//	    }
//
//	    @ExceptionHandler(Exception.class)
//	    public ResponseEntity<String> handleGenericException(Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
//	    }
//	}
//
//}
