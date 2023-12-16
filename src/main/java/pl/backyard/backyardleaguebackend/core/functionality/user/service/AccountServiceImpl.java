package pl.backyard.backyardleaguebackend.core.functionality.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.role.domain.Role;
import pl.backyard.backyardleaguebackend.core.functionality.role.service.RoleQueryService;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.RegisterDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.mapper.UserMapper;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserCudService;
import pl.backyard.backyardleaguebackend.security.helper.PasswordHelper;

import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserCudService userCudService;
    private final RoleQueryService roleQueryService;
    private final PasswordHelper passwordHelper;

    @Override
    public RegisterDTO register(RegisterDTO dto) {
        var encryptedPassword = passwordHelper.encryptPassword(dto.password());
        var user = UserMapper.build(dto, encryptedPassword);
        var roles = roleQueryService.getAllByNames(dto.roles());
        user.addRoles(roles);
        var created = userCudService.create(user);
        var userRoles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        return RegisterDTO.builder()
                .username(created.getUsername())
                .name(created.getName())
                .lastname(created.getLastname())
                .password(created.getPassword())
                .email(created.getEmail())
                .roles(userRoles)
                .build();
    }

}
