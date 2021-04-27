package kz.balthazar.eve.model;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class ApiError {
    private Timestamp timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
