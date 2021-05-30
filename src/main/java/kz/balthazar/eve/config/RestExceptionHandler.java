package kz.balthazar.eve.config;

import kz.balthazar.eve.model.ApiError;
import kz.balthazar.eve.util.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception e, WebRequest request) {
        e.printStackTrace();
        HttpStatus status = switch (e.getMessage()){
//            case Errors.invalidCreds -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(errorMessage(status, e, request), status);
    }

    private ApiError errorMessage(HttpStatus status, Exception ex, WebRequest request) {
        final String message = ex.getMessage() == null || ex.getMessage().isEmpty() ? Errors.somethingWrong : ex.getMessage();
        if (message.equals(Errors.somethingWrong))
            log.error(Arrays.toString(ex.getStackTrace()));
        else
            log.error(message);
        return ApiError.builder()
                .timeStamp(Timestamp.from(Instant.now()))
                .status(status.value())
                .message(message)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .error(status.name())
                .build();
    }
}


/*
{
"timestamp": "2021-03-30T15:39:22.870+00:00",
"status": 500,
"error": "Internal Server Error",
"message": "",
"path": "/test"
}
 */