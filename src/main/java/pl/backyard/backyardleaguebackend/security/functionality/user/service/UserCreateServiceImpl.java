package pl.backyard.backyardleaguebackend.security.functionality.user.service;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.role.service.RoleQueryService;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserCudService;
import pl.backyard.backyardleaguebackend.security.functionality.role.helper.RoleHelper;
import pl.backyard.backyardleaguebackend.security.functionality.user.mapper.UserToAuthenticatedUserMapper;
import pl.backyard.backyardleaguebackend.security.functionality.user.model.AuthenticatedUser;

import java.util.Collections;

@Service
@Transactional
@AllArgsConstructor
public class UserCreateServiceImpl implements UserCreateService {

    private final UserCudService userCudService;
    private final RoleQueryService roleQueryService;

    @Override
    public AuthenticatedUser create(Jwt jwt) {
        var username = jwt.getClaim("preferred_username").toString();
        var name = jwt.getClaim("given_name").toString();
        var lastName = jwt.getClaim("family_name").toString();
        var email = jwt.getClaim("email").toString();

        var roles = roleQueryService.getAllByNames(Collections.singleton(RoleHelper.ROLE_DEFAULT));
        var user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setLastname(lastName);
        user.setUsername(username);
        user.addRoles(roles);
        var created = userCudService.create(user);

        return UserToAuthenticatedUserMapper.mapToAuthenticatedUser(created);
    }


}
