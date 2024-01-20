package pl.backyard.backyardleaguebackend.core.functionality.match.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.MatchStatus;
import pl.backyard.backyardleaguebackend.core.functionality.result.dto.ResultDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamInformationDTO;

import java.time.LocalDateTime;

@Builder
public record MatchDTO(Long id, String location, String comment, MatchStatus status,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime matchTime,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime challengedAt,
                       GameType type, TeamInformationDTO challenger, TeamInformationDTO challenged, ResultDTO result) {

}
