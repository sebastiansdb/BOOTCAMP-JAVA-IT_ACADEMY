package com.S1T2N123.config;

import com.S1T2N123.model.services.interfaces.UserService;
import com.S1T2N123.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtFilter;
    private final UserService userService;

    // @Order(SecurityProperties.BASIC_AUTH_ORDER)
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authHttp -> authHttp
                        .requestMatchers(new AntPathRequestMatcher("/players/auth/**"))
                        .permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/index.html")).permitAll()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){             // 1)
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        // indico qué UserDetailsService usar, para saber qué información de usuario extraer y de dónde lo
        // haremos, ya sea de la BBDD u otro sitio
        daoAuthProvider.setUserDetailsService(userService.userDetailsService()); // se hace referencia al método de arriba.
        daoAuthProvider.setPasswordEncoder(passwordEncoder()); // indico el encoder del password, para poder hacer el decoder
        return daoAuthProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @EnableWebSecurity
        Esta anotación le indica a SpringBoot que esta clase es una clase de Configuración y que dicha configuración
    que contiene, es la de Seguridad.
    Por lo tanto, con esta anotación, lo primero que buscará SpringBoot es un bean de tipo "Security Filter Chain".
    Dicho bean, lo colocamos dentro de esta misma clase y lo saco de la clase "SpringBootWebSecurityConfiguration"

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception { ... }

        Al colocar el bean en este método, SpringBoot irá allí para implementar el "security filter chain". Si no
    colocaríamos dicho bean, SpringBoot iría a buscar el "security filter chain" por DEFECTO, que es el de la clase
    "SecurityFilterChainConfiguration"

    @Order(SecurityProperties.BASIC_AUTH_ORDER)
        No coloco la anotación @Order de arriba. Si lo haría, SpringBoot estaría dando un nivel de seguridad
    básico "Basic Authentication Order"). Por lo tanto, no lo coloco y crearé mi propio nivel de seguridad.

     1)
        AuthenticationProvider es un data access object, responsable de obtener los UserDetails, codificar el password
     y demás cosas.
     */
}

