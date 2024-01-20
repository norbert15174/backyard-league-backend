package pl.backyard.backyardleaguebackend.core.functionality.user.dto;

import lombok.Builder;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamBaseDTO;

import java.util.List;

@Builder
public record ProfileDTO(Long id,
                         String name,
                         String lastname,
                         String username,
                         String email,
                         List<TeamBaseDTO> teams) {
}
