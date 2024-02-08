package br.com.jujubaprojects.restappspringboot.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.jujubaprojects.restappspringboot.data.v1.security.TokenVO;
import br.com.jujubaprojects.restappspringboot.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider  {
    
    // Define a chave secreta padrão para assinar e verificar os tokens JWT
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    // Define o tempo de validade padrão para os tokens JWT (em milissegundos)
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1 hora
    
    // Injeta o serviço que carrega os detalhes do usuário
    @Autowired
    private UserDetailsService userDetailsService;
    
    // Algoritmo usado para assinar e verificar os tokens JWT
    Algorithm algorithm = null;

    // Método executado após a inicialização do bean
    @PostConstruct
    protected void init() {
        // Codifica a chave secreta em Base64
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        // Define o algoritmo usado para assinar os tokens
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    // Cria um novo token de acesso JWT
    public TokenVO createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        // Gera o token de acesso JWT e o token de atualização
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);
        
        return new TokenVO();
    }

    // Atualiza um token de acesso JWT
    public TokenVO refreshToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) refreshToken =
            refreshToken.substring("Bearer ".length());
        
        // Verifica e decodifica o token de atualização
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        // Cria um novo token de acesso com base nas informações do usuário
        return createAccessToken(username, roles);
    }
    
    // Gera um token de acesso JWT
    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString();
        // Cria o token de acesso JWT usando as informações do usuário
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }
    
    // Gera um token de atualização JWT
    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        // Cria o token de atualização JWT usando as informações do usuário
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }
    
    // Obtém a autenticação do usuário com base no token JWT
    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Decodifica o token JWT fornecido
    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public String resolveToken(HttpServletRequest req) {
        // Obtém o token JWT do cabeçalho Authorization da solicitação HTTP
        String bearerToken = req.getHeader("Authorization");
        
        // Verifica se o token JWT está presente e começa com "Bearer "
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Retorna o token JWT sem o prefixo "Bearer "
            return bearerToken.substring("Bearer ".length());
        }
        // Retorna null se o token JWT não estiver presente ou não estiver no formato adequado
        return null;
    }
    
    public boolean validateToken(String token) {
        // Decodifica o token JWT fornecido
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            // Verifica se o token JWT expirou
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
            // Retorna true se o token JWT ainda estiver válido
            return true;
        } catch (Exception e) {
            // Lança uma exceção se o token JWT estiver inválido ou expirado
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
        }
    }
    
}
