package br.com.secret.friend.infra.exceptions;

public class UserGroupNotFoundException extends RuntimeException {
    public UserGroupNotFoundException(String message) {
        super(message);
    }
}