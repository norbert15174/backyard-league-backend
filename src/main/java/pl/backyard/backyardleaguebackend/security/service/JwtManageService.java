package pl.backyard.backyardleaguebackend.security.service;

import io.jsonwebtoken.Claims;
import pl.backyard.backyardleaguebackend.security.model.AuthenticatedUser;


public interface JwtManageService {
    String createAccessToken(AuthenticatedUser user, String subject);

    String createRefreshToken(String username);

    boolean isExpired(String token);

    boolean verify(String token);

    Claims getClaims(String jwtToken);
}
