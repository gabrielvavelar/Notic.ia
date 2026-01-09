package io.github.gabrielvavelar.Noticia.exception;

import io.github.gabrielvavelar.Noticia.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NewsScrapingException.class)
    public ResponseEntity<ErrorResponseDto> handleNewsScrapingException(NewsScrapingException ex){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String path = "scheduler:news-scraper";

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidSummaryInputException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidSummaryInputException(Exception ex){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String path = "summary:input-validation";

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(SummaryGenerationException.class)
    public ResponseEntity<ErrorResponseDto> handleSummaryGenerationException(Exception ex){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String path = "summary:generation";

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExistsException(Exception ex, HttpServletRequest request){

        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidUnsubscribeTokenException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidUnsubscribeTokenException(Exception ex, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        String path = request != null
                ? request.getRequestURI()
                : "Invalid application configuration";

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                path
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex, HttpServletRequest request){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String path = request != null
                ? request.getRequestURI()
                : "internal:unknown";

        String message = "Unexpected internal error";

        ErrorResponseDto error = new ErrorResponseDto(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );

        log.error("Unexpected error {}: {}", path, ex.getMessage(), ex);

        return ResponseEntity.status(status).body(error);
    }

}
