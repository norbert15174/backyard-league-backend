package pl.backyard.backyardleaguebackend.core.functionality.match.dto;

import lombok.Builder;
import pl.backyard.backyardleaguebackend.core.functionality.result.dto.ResultOpinionDTO;

@Builder
public record MatchOpinionDTO(Long id, MatchOpinionStatus status, ResultOpinionDTO result) {
}
