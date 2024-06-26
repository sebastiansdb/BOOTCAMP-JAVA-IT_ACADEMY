package com.S05T02N123.model.services.implementations;

import com.S05T02N123.model.services.interfaces.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String SECRET_KEY;
    @Override
    public String extractUserEmail(String token) {
        // el "Subject" deberia ser el userName o userEmail.
        return extractClaim(token, Claims::getSubject);
    }

    // Metodo que extrae todos los claims del jwt token (en la estructura JWT, los claims es el payload, o sea la data)
    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSingINKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    @Override
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSingINKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey getSingINKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
/*
        EXPLICACION METODO extractAllClaims(String token)

        return Jwts
                .parser()

                    Inicia el proceso de parseo. El término "parseo" se refiere al proceso de analizar una cadena de
                    texto o datos estructurados para interpretar su significado y convertirlos en una estructura de
                    datos que pueda ser manipulada y utilizada por un programa o sistema.
                    En el contexto de los tokens JWT (JSON Web Tokens), el parseo se refiere al proceso de analizar el
                    token JWT para extraer y validar su contenido, como los claims y la firma, para asegurarse de que
                    el token es válido y seguro.

                .verifyWith(getSingINKey())

                    Verifica la firma del JWT utilizando una clave de verificación. La clave se obtiene del método
                    getSingINKey(). Esta clave es necesaria para asegurarse de que el JWT no ha sido alterado y fue
                    firmado correctamente.

                .build()

                    Construye el JwtParser con la clave de verificación proporcionada en el paso anterior.

                .parseSignedClaims(token)

                    Parsea el token JWT que se pasa como argumento y obtiene los claims verificados del token.

                .getPayload();

                    Obtiene el payload del token, que en este caso son los claims, y lo devuelve como un objeto Claims.

     */