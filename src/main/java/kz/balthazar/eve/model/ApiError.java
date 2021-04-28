package kz.balthazar.eve.model;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class ApiError {
    private final Timestamp timeStamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
}
