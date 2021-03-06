package com.isadora.oscarjpa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        super.configure(auth);

    }

    @Override
    public void configure(WebSecurity web) throws  Exception{
        super.configure(web);

    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests()

                .antMatchers(HttpMethod.POST, "/vencedor").permitAll()
                .and().csrf().disable();

    }

}
