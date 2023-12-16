package pl.backyard.backyardleaguebackend.security.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.backyard.backyardleaguebackend.security.model.AuthenticatedUser;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserHelper {

    public static AuthenticatedUser getAuthenticatedUser() {
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
