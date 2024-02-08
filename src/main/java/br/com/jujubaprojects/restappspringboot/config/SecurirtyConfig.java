package br.com.jujubaprojects.restappspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.jujubaprojects.restappspringboot.security.jwt.JwtTokenProvider;

@Configuration
public class SecurirtyConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtTokenProvider tokenProvider;

    public SecurirtyConfig(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){

    }
}
