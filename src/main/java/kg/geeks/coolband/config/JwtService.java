package kg.geeks.coolband.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.jwt.secret}")
    @Getter
    private String SECRET_KEY;

    private final String TOKEN_USERNAME = "username";

    public String generateToken(UserDetails userDetails){
        return JWT.create()
                .withClaim(TOKEN_USERNAME,userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(2000).toInstant()))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateToken(String token) {
        JWTVerifier jwtVerifier =
                JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim(TOKEN_USERNAME).asString();
    }
}
