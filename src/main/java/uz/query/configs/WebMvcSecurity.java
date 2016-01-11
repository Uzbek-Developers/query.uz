package uz.query.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import uz.query.security.CustomAuthenticationProvider;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Configuration
@EnableWebSecurity
public class WebMvcSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .antMatchers("/tag/**").permitAll()
                .antMatchers("/fill").permitAll()
                .antMatchers("/tag/**").permitAll()
                .antMatchers("/help/**").permitAll()
                .antMatchers("/article/**").permitAll()
                .antMatchers("/question/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/less/**").permitAll()
                .antMatchers("**/edit/{id}","**/add/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .permitAll();
    }

}