package com.bigboss.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bigboss.common.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handle(ResouceNotFoundException ex, HttpServletRequest request){
		
		ErrorResponse errorResponse = ErrorResponse.builder().status(404).error(HttpStatus.NOT_FOUND.name())
				.message(ex.getMessage()).timestamp(java.time.LocalDateTime.now()).path(request.getRequestURI()).build();
				
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		
	}

}
