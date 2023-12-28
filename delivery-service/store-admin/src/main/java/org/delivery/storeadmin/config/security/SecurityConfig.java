package org.delivery.storeadmin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity  //security 활성화
public class SecurityConfig {

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/open-api/**"
    );
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity
    )throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(it->{
                it
                    .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                    ).permitAll()  //resource에 대해서는 모든 요청 허용

                   //swagger는 인증없이 통과
                    .requestMatchers(SWAGGER.toArray(new String[0])).permitAll()

                    //그 외 모든 요청은 인증사용
                    .anyRequest().authenticated();
            })
            .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        //해시 방식으로 암호화
        return new BCryptPasswordEncoder();        
    }
}
