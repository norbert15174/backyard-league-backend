package pl.backyard.backyardleaguebackend.core.functionality.result.dto;

import lombok.Builder;

@Builder
public record ResultOpinionDTO(Integer challengerScore, Integer challengedScore) {

}
