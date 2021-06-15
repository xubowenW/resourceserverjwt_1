package com.xu.springsecurity.resourceserverjwt;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.interfaces.RSAPublicKey;

@EnableWebSecurity
public class ResourceServerSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}")
   // RSAPublicKey key;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/message/**").hasAuthority("SCOPE_message:read")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                        //.jwt()
                               .jwt(jwt ->
                                        jwt.decoder(jwtDecoder())
                                )
                );
        // @formatter:on
    }


    @Bean
    JwtDecoder jwtDecoder() {
        //return NimbusJwtDecoder.withPublicKey(this.key).build();
        return NimbusJwtDecoder.withJwkSetUri("http://127.0.0.1:9000/.well-known/jwks.json").build();
    }




}
