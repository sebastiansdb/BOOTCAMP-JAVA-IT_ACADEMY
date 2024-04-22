//package com.S1T2N123.config;
//
//import com.S1T2N123.model.repositories.UserRepository;
//import com.S1T2N123.security.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//
//    private final UserRepository userRepository;
//    @Bean
//    public UserDetailsService userDetailsService(){
////        Optional<User> userOptional = userRepository.findUserByEmail(userEmail);
//        // OJO: El tutorial que vi, aqui usa el metodo "findUserByEmail(String userEmail)", en vez de "findById()"
//        return userEmail -> userRepository.findById(userEmail).orElseThrow(() ->
//                new UsernameNotFoundException("User not found"));

        // la LAMBDA de arriba sale como recomendación del IDE, tras implementar las siguientes líneas de código:

//        return new UserDetailsService(){
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return null;
//            }
//        };
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(){                                 // 1)
//        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
//        // debemos indicar qué UserDetailsService usar para saber qué información de usuario extraer y de dónde lo
//        // haremos, ya sea de la BBDD u otro sitio
//        daoAuthProvider.setUserDetailsService(userDetailsService()); // se hace referencia al método de arriba.
//        daoAuthProvider.setPasswordEncoder(passwordEncoder()); // indico el encoder del password, para poder hacer el decoder
//        return daoAuthProvider;
//    }
//    /*
//        1) AuthenticationProvider es un data access object, responsable de obtener los UserDetails, codificar el password
//        y demás cosas.
//     */
//
//    @Bean
//    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//}
