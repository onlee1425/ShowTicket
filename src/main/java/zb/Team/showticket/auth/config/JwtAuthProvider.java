package zb.Team.showticket.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zb.Team.showticket.auth.domain.ProviderVo;
import zb.Team.showticket.auth.domain.UserType;
import zb.Team.showticket.auth.domain.UserVo;
import zb.Team.showticket.auth.util.Aes256Util;

import java.util.Date;
import java.util.Objects;

@Configuration
public class JwtAuthProvider {
    public String secretkey = "secretKey";

    private Long tokenValidTime = 1000L * 60 * 60 * 24;

    public String createToken(String userPk, Long id, UserType userType) {
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk)).setId(Aes256Util.encrypt(id.toString()));
        claims.put("roles", userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact();
    }

    // token validation check
    public boolean validationToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(jwtToken); // token parse
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public UserVo getUserVo(String token){
        Claims c = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
        return new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(c.getId())))
                ,Aes256Util.decrypt(c.getSubject()));
    }

    public ProviderVo getProviderVo(String token){
        Claims c = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
        return new ProviderVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(c.getId())))
                ,Aes256Util.decrypt(c.getSubject()));
    }


}
