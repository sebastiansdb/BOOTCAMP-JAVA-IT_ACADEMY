package com.S1T2N123.security;

import com.S1T2N123.model.services.implementations.JwtService;
import com.S1T2N123.model.services.interfaces.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // obtengo el "Authorization Header" de la request que hace el cliente.
        // Cuando hago un request, se pasa el token de autenticación JWT dentro del header ( del request ), el cual es
        // llamado "autorización".
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String jwtToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // Una vez que verifico que el token JWT recibido es correcto, lo extraigo del "Authentication o Authorization Header"
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(jwtToken);
        // verifico que se haya ingresado un email y checkeo si el usuario ya se ha autenticado (para hacer esto me
        // sirvo de la clase "SecurityContextHolder"). En caso afirmativo de esto último, no tendré que ir a checkear
        // en la base de datos si ese usuario existe, justamente porque ya está autenticado
        // Si "SecurityContextHolder.getContext().getAuthentication() == null" es TRUE, significa que el usuario aun
        // no está autentificado o conectado
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            // busco usuario en la BBDD
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            // checkeo que el token proporcionado sea válido para el usuario en cuestión, y que no haya expirado
            if(jwtService.isTokenValid(jwtToken,userDetails)){
                // SECURITY: UPDATE THE SECURITY CONTEXTHOLDER

                // intancio un objeto que será el token de autenticación del password del usuario en cuestión
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // cuando creamos el user, no colocamos credenciales. Por eso, aquí tenemos null
                        userDetails.getAuthorities()
                        );
                // refuerzo el token de autenticación con los detalles del request
                authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
                // actualizo el token de autenticación en el Security Context Holder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            // paso la información al siguiente filtro para que continue la ejecución del proceso
            filterChain.doFilter(request, response);
        }
    }
}
