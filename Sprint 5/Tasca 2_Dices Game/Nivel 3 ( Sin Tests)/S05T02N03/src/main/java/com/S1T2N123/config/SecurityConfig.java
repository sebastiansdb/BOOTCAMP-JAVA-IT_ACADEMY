//package com.S1T2N123.config;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
///*
//    La anotación de abajo le indica a SpringBoot que esta clase es una clase de Configuración y que dicha configuración
//    que contiene, es la de Seguridad.
//    Por lo tanto, con esta anotacion, lo primero que buscará SpringBoot es un bean de tipo "Scurity Filter Chain".
//    Dicho bean, lo colocamos dentro de esta misma clase y lo saco de la clase "SpringBootWebSecurityConfiguration"
// */
//@EnableWebSecurity
//public class SecurityConfig {
//    /*
//        Al colocar este bean, SpringBoot vendrá aquí para implementar el "security filter chain". Si no
//        colocaríamos dicho bean, SpringBoot iría a buscar el "security filter chain" por DEFECTO, que es el de la clase
//        "SecurityFilterChainConfiguration"
//     */
//    @Bean
//    // @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    /*
//        No coloco la anotación @Order de arriba, porque si lo hago, SpringBoot estaría dando un nivel de seguridad
//        básico "Basic Authentication Order"). Por lo tanto, no lo coloco y crearé mi propio nivel de seguridad.
//     */
//    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> {
//            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
//        });
//        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//}
