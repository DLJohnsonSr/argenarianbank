package com.d2j2.argenarianbank.configuration;

import com.d2j2.argenarianbank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepo;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUSD(userRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] everyone = {};
        String[] branch = {};
        String[] corporate = {};

        http.authorizeRequests()
                .antMatchers().authenticated()
                .antMatchers().access("hasAuthority('BM') and hasAuthority('ADMIN') and hasAuthority('CORP')")
                .antMatchers().access("hasAuthority('ADMIN') and hasAuthority('CORP')")
                .antMatchers().access("hasAuthority('CORP')")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("test")
                .password(passwordEncoder().encode("test")).authorities("BM","ADMIN","CORP")
                .and()
                .passwordEncoder(passwordEncoder());

        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
    }
}
