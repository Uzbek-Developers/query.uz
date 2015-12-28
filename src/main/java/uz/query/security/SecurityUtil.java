package uz.query.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uz.query.models.User;

import javax.annotation.PostConstruct;

/**
 * Created by Mirjalol Bahodirov on 12/28/15 with love.
 */
@Component
public class SecurityUtil {

    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User getCurrentUser() {
        if (SecurityContextHolder.getContext()
                .getAuthentication() instanceof CustomAuthenticationToken) {
            return (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }


}
