package kz.balthazar.eve.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    private Map<String, Integer> ipsAndAttempts;

    public LoginAttemptService() {
        ipsAndAttempts = new HashMap<>();
    }

    public void loginSucceeded(String key) {
        ipsAndAttempts.put(key, 0);
    }

    public void loginFailed(String key) {
        int attempts = ipsAndAttempts.get(key);
        attempts++;
        ipsAndAttempts.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        if (ipsAndAttempts.containsKey(key))
            return ipsAndAttempts.get(key) >= MAX_ATTEMPT;
        else {
            ipsAndAttempts.put(key, 0);
            return false;
        }
    }
}