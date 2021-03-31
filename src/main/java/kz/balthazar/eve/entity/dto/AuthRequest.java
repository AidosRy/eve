package kz.balthazar.eve.entity.dto;

import lombok.Data;

@Data
public class AuthRequest {
    String login;
    String password;
}
