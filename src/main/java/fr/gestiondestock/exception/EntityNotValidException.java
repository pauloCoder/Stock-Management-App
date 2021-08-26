package fr.gestiondestock.exception;

import lombok.Getter;

import java.util.List;

public class EntityNotValidException extends RuntimeException{

    @Getter
    private ErrorCodes errorCode;
    @Getter
    private List<String> errors;

    public EntityNotValidException(String message) {
        super(message);
    }

    public EntityNotValidException(String message , Throwable cause) {
        super(message,cause);
    }

    public EntityNotValidException(String message ,  ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public EntityNotValidException(String message , Throwable cause , ErrorCodes errorCode) {
        super(message,cause);
        this.errorCode = errorCode;
    }

    public EntityNotValidException(String message ,  ErrorCodes errorCode , List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }


}
