package pl.backyard.backyardleaguebackend.security.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import pl.backyard.backyardleaguebackend.core.functionality.role.domain.Role;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.util.Collection;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthorityHelper {

    public static Collection<GrantedAuthority> getUserAuthorities(User user) {
        var roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        return AuthorityUtils.createAuthorityList(roles);
    }

}
