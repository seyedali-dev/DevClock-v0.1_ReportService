package com.seyed.ali.reportsservice.exceptions.handler;

import com.seyed.ali.reportsservice.exceptions.OperationNotSupportedException;
import com.seyed.ali.reportsservice.exceptions.ResourceNotFoundException;
import com.seyed.ali.reportsservice.model.payload.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ReportServiceHandlerAdvice {

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(FORBIDDEN)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(FORBIDDEN).body(Map.of(
                "code", FORBIDDEN.toString(),
                "message", "No permission.",
                "server_message", e.getMessage()
        ));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("🚫exception: {}", e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Result(
                false,
                INTERNAL_SERVER_ERROR,
                "Exception Has Occurred!!!",
                "🚫 - " + e.getMessage()
        ));
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Result> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new Result(
                false,
                NOT_FOUND,
                "The requested resource was not found.",
                "ServerMessage - " + e.getMessage()
        ));
    }

    @ExceptionHandler({OperationNotSupportedException.class})
    public ResponseEntity<Result> handleOperationNotSupportedException(OperationNotSupportedException e) {
        return ResponseEntity.status(NOT_FOUND).body(new Result(
                false,
                NOT_FOUND,
                "This operation is not supported.",
                "ServerMessage - " + e.getMessage()
        ));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Result> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(allErrors.size());
        allErrors.forEach(objectError -> {
            String defaultMessage = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            map.put(field, defaultMessage);
        });

        return ResponseEntity.status(BAD_REQUEST).body(new Result(
                false,
                BAD_REQUEST,
                "Provided arguments are invalid, see data for details.",
                map
        ));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Result> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new Result(
                false,
                BAD_REQUEST,
                "Error happened while parsing JSON.",
                e.getMessage()
        ));
    }

    @ExceptionHandler({UnknownHostException.class})
    public ResponseEntity<Result> handleUnknownHostException(UnknownHostException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Result(
                false,
                INTERNAL_SERVER_ERROR,
                "The provided host does is not valid.",
                "ServerMessage: { " + e.getMessage() + " }"
        ));
    }

}
