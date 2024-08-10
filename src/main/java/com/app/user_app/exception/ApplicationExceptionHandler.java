package com.app.user_app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) {
        logger.error("Message: {}\nStackTrace: {}",e.getMessage(),e.getStackTrace());
        return new ResponseEntity<>("Internal Server Error!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAllreadyExistsException(EmailAlreadyExistsException e) {
        logger.error("Message: {}\nStackTrace: {}",e.getMessage(),e.getStackTrace());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordExceptionException(InvalidPasswordException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationUserNotFoundException.class)
    public ResponseEntity<?> handleApplicationUserNotFoundException(ApplicationUserNotFoundException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
    }
}