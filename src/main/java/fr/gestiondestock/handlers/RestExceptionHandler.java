package fr.gestiondestock.handlers;

import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception , WebRequest webRequest) {

        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                                          .httpCode(notFound.value())
                                          .errorCode(exception.getErrorCode())
                                          .message(exception.getMessage())
                                          .build();

        return new ResponseEntity<>(errorDto , notFound);
    }

    @ExceptionHandler(EntityNotValidException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotValidException exception , WebRequest webRequest) {

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                                          .httpCode(badRequest.value())
                                          .errorCode(exception.getErrorCode())
                                          .message(exception.getMessage())
                                          .errors(exception.getErrors())
                                          .build();

        return new ResponseEntity<>(errorDto , badRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleExeption(BadCredentialsException exception , WebRequest webRequest) {

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .httpCode(badRequest.value())
                .errorCode(ErrorCodes.BAD_CREDENTIALS)
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login et / ou mot depasse incorrects"))
                .build();

        return new ResponseEntity<>(errorDto , badRequest);

    }

}
