package pl.backyard.backyardleaguebackend.core.functionality.team.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamInformationDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamLadderDTO;

public interface TeamSearchService {
    TeamInformationDTO getTeamInformation(Long id);

    Page<TeamLadderDTO> getAll(Specification<Team> spec, Pageable pageable);
}
