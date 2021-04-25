package kz.balthazar.eve.model.dto;

import lombok.Data;

@Data
public class AuthRequest {
    String login;
    String password;
}
