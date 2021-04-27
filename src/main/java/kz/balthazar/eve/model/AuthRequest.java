package kz.balthazar.eve.model;

import lombok.Data;

@Data
public class AuthRequest {
    String login;
    String password;
}
