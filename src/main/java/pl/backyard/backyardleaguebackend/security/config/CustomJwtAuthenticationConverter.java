package pl.backyard.backyardleaguebackend.security.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import pl.backyard.backyardleaguebackend.core.functionality.role.domain.Role;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.functionality.user.cache.UserCache;
import pl.backyard.backyardleaguebackend.security.functionality.user.mapper.UserToAuthenticatedUserMapper;
import pl.backyard.backyardleaguebackend.security.functionality.user.service.UserCreateService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final UserQueryService userQueryService;
    private final UserCache userCache;
    private final UserCreateService userCreateService;

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        var username = jwt.getClaim("preferred_username").toString();
        var sessionState = jwt.getClaim("session_state").toString();
        var roles = userCache.getRolesByUsername(username, sessionState);
        if (roles.isPresent()) {
            return getGrantedAuthorities(roles.get());
        }

        var userOpt = userQueryService.getOptByUsername(username);
        if (userOpt.isEmpty()) {
            log.debug("Create user while processing token: {}", username);
            var user = userCreateService.create(jwt);
            return getGrantedAuthorities(userCache.putAndGetUserRole(user, sessionState));
        }

        userCache.putAndGetUserRole(UserToAuthenticatedUserMapper.mapToAuthenticatedUser(userOpt.get()), sessionState);
        var userRoles = userOpt.get()
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return getGrantedAuthorities(userRoles);
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
