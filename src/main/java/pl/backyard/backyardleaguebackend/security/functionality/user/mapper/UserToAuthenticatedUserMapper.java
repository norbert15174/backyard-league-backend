package pl.backyard.backyardleaguebackend.security.functionality.user.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.backyard.backyardleaguebackend.core.functionality.role.domain.Role;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;
import pl.backyard.backyardleaguebackend.security.functionality.user.model.AuthenticatedUser;

import java.util.Locale;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToAuthenticatedUserMapper {

    public static AuthenticatedUser mapToAuthenticatedUser(User created) {
        var userRoles = created.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        return AuthenticatedUser.builder()
                .id(created.getId())
                .username(created.getUsername())
                .firstname(created.getName())
                .lastname(created.getLastname())
                .email(created.getEmail())
                .locale("pl_PL")
                .roles(userRoles)
                .build();
    }

}
