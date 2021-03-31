package kz.balthazar.eve.config;

import kz.balthazar.eve.entity.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception e, WebRequest request) {
        log.info(e.getMessage());
        var body = new HashMap<String, String>();
        var ldt = LocalDateTime.now();
        var zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        var gmt = zdt.withZoneSameInstant(ZoneId.of("GMT"));
        var timestamp = Timestamp.valueOf(gmt.toLocalDateTime());
        body.put("timestamp", timestamp.toString());
        body.put("status", "500");
        body.put("error", "Internal Server Error");
        body.put("message", e.getMessage());
        body.put("path","");
        return new ResponseEntity<Object>(errorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e, request), HttpStatus.INTERNAL_SERVER_ERROR);
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(body.toString());
        /*
        {
    "timestamp": "2021-03-30T15:39:22.870+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "",
    "path": "/test"
}
         */
    }

    private ApiError errorMessage(HttpStatus httpStatus, Exception ex, WebRequest request) {
        final String message = ex.getMessage() == null ? ex.getClass().getName() : ex.getMessage();
        final String developerMessage = ex.getCause() == null ? ex.toString() : ex.getCause().getMessage();
        return ApiError.builder()
                .status(321)
                .message(message)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .developerMessage(developerMessage)
                .build();
    }
}
