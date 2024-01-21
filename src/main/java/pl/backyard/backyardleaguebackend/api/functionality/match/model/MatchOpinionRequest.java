package pl.backyard.backyardleaguebackend.api.functionality.match.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchOpinionStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MatchOpinionRequest {

    private MatchOpinionStatus status;
    private Result result;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Result {
        private Integer challengerScore;
        private Integer challengedScore;
    }

}
