package pl.backyard.backyardleaguebackend.core.functionality.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamBaseDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.ProfileDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.mapper.ProfileMapper;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.functionality.user.context.UserContextHolder;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
class UserSearchServiceImpl implements UserSearchService {

    private final UserQueryService userQueryService;

    @Override
    public ProfileDTO getProfile() {
        var authenticatedUser = UserContextHolder.getAuthenticatedUser();
        var user = userQueryService.getByIdWithTeams(authenticatedUser.id());
        return mapAndGet(user);
    }

    @Override
    public ProfileDTO getUserInfo(Long id) {
        var user = userQueryService.getByIdWithTeams(id);
        return mapAndGet(user);
    }

    private ProfileDTO mapAndGet(User user) {
        var teams = user.getTeams().stream()
                .map(userTeam -> TeamBaseDTO.builder()
                        .id(userTeam.getTeam().getId())
                        .name(userTeam.getTeam().getName())
                        .type(userTeam.getTeam().getType())
                        .owner(userTeam.getRole())
                        .build()
                ).toList();

        return ProfileMapper.map(teams, user);
    }

}
