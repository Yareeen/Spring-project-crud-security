package com.example.rest_h2_jpa_lombok.auth;


import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private JwtAuthEntryPoint authEntryPoint;


    private CustomerDetailsService customerDetailsService;

    public SecurityConfig(JwtAuthEntryPoint authEntryPoint, CustomerDetailsService customerDetailsService) {
        this.authEntryPoint = authEntryPoint;
        this.customerDetailsService = customerDetailsService;
    }



    @Bean
    public JwtEncoder jwtEncoder() {
        String secret = "dfgdfg";
        SecretKey key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<SecurityContext>(key);
        return new NimbusJwtEncoder(immutableSecret);

    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http
                .csrf().disable() //csrf'i geçemeyiz. Disable etmezsek
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate/**").permitAll() //izin verilir. authanticate ile başlayan tüm urı'e
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
        /*Bu yapıda, HttpSecurity sınıfı kullanılarak güvenlik ayarları yapılır.
        Bu kod parçasında, CSRF koruması devre dışı bırakılır ve “/authenticate” endpoint’i herkese açık hale getirilir.
         Ayrıca, diğer tüm isteklerin kimlik doğrulaması gerektiği belirtilir.
          */
    }



    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean //şifrenin kodlanarak veritabanına eklenmesini sağlar.
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();

    }

}


