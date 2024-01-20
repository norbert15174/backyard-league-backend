package pl.backyard.backyardleaguebackend.security.functionality.user.service;

import org.springframework.security.oauth2.jwt.Jwt;
import pl.backyard.backyardleaguebackend.security.functionality.user.model.AuthenticatedUser;

public interface UserCreateService {
    AuthenticatedUser create(Jwt jwt);
}
