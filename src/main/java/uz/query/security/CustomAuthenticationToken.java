package uz.query.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import uz.query.models.User;

import java.util.Collection;

/**
 * Created by Mirjalol Bahodirov on 12/28/15 with love.
 */
public class CustomAuthenticationToken implements Authentication {

    private User user;

    public CustomAuthenticationToken(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getDisplayName();
    }
}
