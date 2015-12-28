package uz.query.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import uz.query.models.User;
import uz.query.repositories.UserRepository;

/**
 * Created by Mirjalol Bahodirov on 12/28/15 with love.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.getUserByPrincipial(
                authentication.getName(),
                authentication.getCredentials().toString()
        );
        Authentication authenticationToken = null;
        if (user != null) {
            authenticationToken = new CustomAuthenticationToken(user);
        }
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
