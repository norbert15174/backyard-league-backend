package pl.backyard.backyardleaguebackend.core.functionality.team.dto;

import lombok.Builder;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.TeamRoleType;

@Builder
public record TeamBaseDTO(Long id, String name, GameType type, TeamRoleType owner) {
}
