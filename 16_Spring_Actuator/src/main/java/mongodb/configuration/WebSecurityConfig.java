package mongodb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SuccessUserHandler successUserHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/user/*").access("hasAnyRole('ROLE_USER')")
                .antMatchers("/admin/**", "/user/**").access("hasAnyRole('ROLE_ADMIN')")
//                .antMatchers("/admin/**", "/user/**", "/actuator/**").access("hasAnyRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginProcessingUrl("/perform_login").permitAll()
                .successHandler(successUserHandler)
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .invalidateHttpSession(true);
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
