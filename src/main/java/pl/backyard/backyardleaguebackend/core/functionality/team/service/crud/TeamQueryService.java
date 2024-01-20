package pl.backyard.backyardleaguebackend.core.functionality.team.service.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamCodeDTO;

public interface TeamQueryService {
    Team getByCode(String code);

    TeamCodeDTO getCodeById(Long id);

    Team getByIdWithMembers(Long id);

    long countTeamsWithMorePoints(Team team);

    Page<Team> getAll(Specification<Team> spec, Pageable pageable);
}
