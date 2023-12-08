package com.github.aleffalves.apiblog.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.aleffalves.apiblog.dto.TokenDTO;
import com.github.aleffalves.apiblog.model.Usuario;
import com.github.aleffalves.apiblog.utils.ConstantsUtils;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private String secretKey = "secret";
    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(Usuario usuario){
        Date dataAtual = new Date();
        Date validade = new Date(dataAtual.getTime() + ConstantsUtils.TOKEN_EXPIRATION);

        var acessToken = getAccessToken(usuario, dataAtual, validade);

        return new TokenDTO(usuario.getEmail(), true, dataAtual, validade, acessToken);
    }

    private String getAccessToken(Usuario usuario, Date dataAtual, Date validade) {
        String urlServidor = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString();


        return JWT.create()
                .withClaim("id",usuario.getId())
                .withClaim( "roles" , usuario.getRoles())
                .withIssuedAt(dataAtual)
                .withExpiresAt(validade)
                .withSubject(usuario.getEmail())
                .withIssuer(urlServidor)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();

        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validadeToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try{
            return !decodedJWT.getExpiresAt().before(new Date());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Jwt expirado ou inválido.");
        }
    }

}
