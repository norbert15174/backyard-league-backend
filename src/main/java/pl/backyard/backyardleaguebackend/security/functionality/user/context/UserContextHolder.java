package pl.backyard.backyardleaguebackend.security.functionality.user.context;

import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import pl.backyard.backyardleaguebackend.security.config.AppContextAware;
import pl.backyard.backyardleaguebackend.security.functionality.user.cache.UserCache;
import pl.backyard.backyardleaguebackend.security.functionality.user.model.AuthenticatedUser;


@NoArgsConstructor
public class UserContextHolder {

    public static AuthenticatedUser getAuthenticatedUser() {
        var claims = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = claims.getClaims().get("preferred_username").toString();
        return AppContextAware.getApplicationContext().getBean(UserCache.class).getAuthenticatedUser(username);

    }

}
