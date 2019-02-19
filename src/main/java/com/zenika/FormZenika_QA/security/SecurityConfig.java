/*
package com.zenika.FormZenika_QA.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//class configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin")
                .password("1234")
                .roles("USER","ADMIN");
        auth.inMemoryAuthentication().withUser("user")
                .password("0123")
                .roles("USER");
    }

    //dèfinir les regles de security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //form d'authentification que se spring security va le cree par default
        */
/*Requete http dont l'url est /questions necessite une auth avec
        un utilisateur de type USER *//*

        */
/*http.formLogin();
        http.authorizeRequests().antMatchers("/questions").hasRole("USER");
        http.authorizeRequests().
                antMatchers("/delete","/edit","save","/formQuestion").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");*//*

        http.formLogin();
        http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
        http.authorizeRequests().
                antMatchers("/admin/*").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");

    }
}
*/
