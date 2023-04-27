package org.ssafy.sample.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.ssafy.sample.exception.NotValidUserException;

@ControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException() {
		System.out.println("널포인터 에러");
		return new ResponseEntity<>("실패", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotValidUserException.class)
	public ResponseEntity<String> handleNotValidUserException() {
		System.out.println("비밀번호 틀림");
		return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
	}
}
