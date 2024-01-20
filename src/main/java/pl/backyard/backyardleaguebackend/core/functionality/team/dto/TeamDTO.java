package pl.backyard.backyardleaguebackend.core.functionality.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;

@Builder
public record TeamDTO(Long id, String name, GameType type, Long points, boolean owner) {

}
