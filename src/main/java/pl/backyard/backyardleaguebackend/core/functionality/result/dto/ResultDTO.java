package pl.backyard.backyardleaguebackend.core.functionality.result.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.ResultStatus;

@AllArgsConstructor
@Getter
public class ResultDTO {

    private final Integer challengerScore;
    private Integer challengedScore;
    private ResultStatus status;

}
