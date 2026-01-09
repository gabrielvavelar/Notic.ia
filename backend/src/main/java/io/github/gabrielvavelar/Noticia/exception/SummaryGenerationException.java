package io.github.gabrielvavelar.Noticia.exception;

public class SummaryGenerationException extends RuntimeException {
    public SummaryGenerationException(String message) {
        super(message);
    }

    public SummaryGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
