package cohen.cameron.tasks.controller;

import cohen.cameron.tasks.exceptions.TaskNotFoundException;
import cohen.cameron.tasks.valueobjects.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleTaskNotFound(TaskNotFoundException e, WebRequest request) {
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUuidMismatched(MethodArgumentTypeMismatchException e, WebRequest request) {
        return new ResponseEntity<>(
                "Not a valid UUID",
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("You have invalid method args");
        List<ErrorInfo> errors = ex.getFieldErrors().stream().map(fieldError ->
                new ErrorInfo(fieldError.getCode(), fieldError.getField(), (String) fieldError.getRejectedValue(), fieldError.getDefaultMessage())
        ).toList();
        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }
}

