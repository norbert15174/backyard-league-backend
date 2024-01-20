package pl.backyard.backyardleaguebackend.core.functionality.team.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamInformationDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamLadderDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;

import java.util.ArrayList;
import java.util.Comparator;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
class TeamSearchServiceImpl implements TeamSearchService {

    private final TeamQueryService queryService;

    @Override
    public TeamInformationDTO getTeamInformation(Long id) {
        var team = queryService.getByIdWithMembers(id);
        var members = team.getUsers().stream().map(member -> {
                    var user = member.getUser();
                    var role = member.getRole();
                    return TeamInformationDTO.Members.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .firstname(user.getName())
                            .lastname(user.getLastname())
                            .role(role)
                            .build();
                }).sorted(Comparator.comparing(TeamInformationDTO.Members::getUsername))
                .toList();


        return TeamInformationDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .type(team.getType())
                .points(team.getPoints())
                .rank(queryService.countTeamsWithMorePoints(team) + 1)
                .members(members)
                .build();
    }

    @Override
    public Page<TeamLadderDTO> getAll(Specification<Team> spec, Pageable pageable) {
        var entities = queryService.getAll(spec, pageable);

        if (entities.isEmpty()) {
            return entities.map(entity -> TeamLadderDTO.builder()
                    .build()
            );
        }

        var entity = entities.getContent()
                .stream()
                .sorted(Comparator.comparing(Team::getPoints).reversed())
                .toList()
                .iterator()
                .next();

        var rank = queryService.countTeamsWithMorePoints(entity) + 1;

        var dtos = new ArrayList<TeamLadderDTO>();
        for (var team : entities) {
            TeamLadderDTO teamLadderDTO = TeamLadderDTO.builder()
                    .id(team.getId())
                    .points(team.getPoints())
                    .type(team.getType())
                    .name(team.getName())
                    .position(rank)
                    .build();

            dtos.add(teamLadderDTO);
            rank++;
        }

        return new PageImpl<>(dtos, entities.getPageable(), entities.getTotalElements());
    }

}
