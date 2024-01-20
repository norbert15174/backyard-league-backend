package pl.backyard.backyardleaguebackend.core.functionality.team.dto;

import lombok.Builder;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;

@Builder
public record TeamLadderDTO(Long id, String name, GameType type, Long points, Long position) {

}
