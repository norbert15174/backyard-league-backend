package pl.backyard.backyardleaguebackend.core.functionality.result.dto;

import lombok.Builder;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.ResultStatus;

@Builder
public record ResultDTO(Integer challengerScore, Integer challengedScore, ResultStatus status) {

}
