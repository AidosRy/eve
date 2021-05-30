package kz.balthazar.eve.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BruteForceService {

    private final int MAX_ATTEMPT = 5;
    private Map<String, Integer> ipsAndAttempts;

    public BruteForceService() {
        ipsAndAttempts = new HashMap<>();
    }

    public void loginSucceeded(String ip) {
        ipsAndAttempts.put(ip, 0);
    }

    public void loginFailed(String ip) {
        int attempts = ipsAndAttempts.get(ip);
        attempts++;
        ipsAndAttempts.put(ip, attempts);
    }

    public boolean isBlocked(String ip) {
        if (ipsAndAttempts.containsKey(ip))
            return ipsAndAttempts.get(ip) >= MAX_ATTEMPT;
        else {
            ipsAndAttempts.put(ip, 0);
            return false;
        }
    }
}