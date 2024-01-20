package pl.backyard.backyardleaguebackend.security.functionality.user.cache;

import pl.backyard.backyardleaguebackend.security.functionality.user.model.AuthenticatedUser;

import java.util.Optional;
import java.util.Set;

public interface UserCache {
    Set<String> putAndGetUserRole(AuthenticatedUser user, String session);

    Optional<Set<String>> getRolesByUsername(String username, String session);

    AuthenticatedUser getAuthenticatedUser(String username);

}
