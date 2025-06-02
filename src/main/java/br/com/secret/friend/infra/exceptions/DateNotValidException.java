package br.com.secret.friend.infra.exceptions;

public class DateNotValidException extends RuntimeException {
    public DateNotValidException(String message) {
        super(message);
    }
}
