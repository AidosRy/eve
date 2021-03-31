package kz.balthazar.eve.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiError {
    private Long timeStamp;
    private int status;
    private String message;
    private String developerMessage;
    private String path;
}
