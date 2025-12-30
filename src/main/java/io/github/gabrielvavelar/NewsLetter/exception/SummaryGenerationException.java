package io.github.gabrielvavelar.NewsLetter.exception;

public class SummaryGenerationException extends RuntimeException {
    public SummaryGenerationException(String message) {
        super(message);
    }

    public SummaryGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
