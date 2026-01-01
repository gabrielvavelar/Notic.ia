package io.github.gabrielvavelar.NewsLetter.exception;

public class EmailDoesntExistsException extends RuntimeException{
    public EmailDoesntExistsException(String message){
        super(message);
    }
}
