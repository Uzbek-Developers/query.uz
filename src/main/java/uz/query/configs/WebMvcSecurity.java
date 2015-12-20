package uz.query.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Configuration
public class WebMvcSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/*").permitAll()
//                .anyRequest().authenticated();
//        http.csrf().disable();
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/js/**","/css/**", "/**").permitAll()
//                .anyRequest().permitAll()
//                .and();

        http
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .anyRequest().hasAnyRole("USER")
                .and();
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", false)
                .permitAll();
        http
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

    }

}