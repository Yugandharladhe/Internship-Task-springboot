package com.task.backend.db.mongodb.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.task.backend.db.mongodb.responses.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(IOException.class)
//	public ResponseEntity<ApiResponse> handleMethodArgsNotValidException(Exception ex) {
////		Map<String,String> exc=new HashMap<>();
//		// here return all errors in form of key value pair
//		// {fieldname:its default message}
//		return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
//	}
	
	
}

