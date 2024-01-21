package pl.backyard.backyardleaguebackend.core.functionality.match.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.MatchStatus;

import java.time.LocalDateTime;

@Builder
public record MatchBaseDTO(Long id, String name, MatchStatus status, Long challenger, Long challenged,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime matchTime) {
}
