package io.github.gabrielvavelar.Noticia.exception;

public class NewsScrapingException extends RuntimeException{
    public NewsScrapingException(String message,  Throwable cause) {
        super(message, cause);
    }
}
