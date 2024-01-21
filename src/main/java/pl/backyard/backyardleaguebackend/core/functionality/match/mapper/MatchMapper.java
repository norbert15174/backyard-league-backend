package pl.backyard.backyardleaguebackend.core.functionality.match.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.result.dto.ResultDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.TeamSearchService;

import java.util.Objects;

@AllArgsConstructor
@Component
public class MatchMapper {

    private final TeamSearchService teamSearchService;

    public MatchDTO mapToMatchDTO(Team challenger, Team challenged, Match match) {
        var challengedDTO = teamSearchService.getTeamInformation(challenged.getId());
        var challengerDTO = teamSearchService.getTeamInformation(challenger.getId());


        var matchDTO = MatchDTO.builder()
                .id(match.getId())
                .location(match.getLocation())
                .comment(match.getComment())
                .challengedAt(match.getChallengedAt())
                .matchTime(match.getMatchTime())
                .challenged(challengedDTO)
                .challenger(challengerDTO)
                .status(match.getStatus())
                .type(match.getType());

        if (Objects.nonNull(match.getResult())) {
            var result = match.getResult();
            var resultDTO = ResultDTO.builder()
                    .challengedScore(result.getChallengedScore())
                    .challengerScore(result.getChallengerScore())
                    .status(result.getStatus())
                    .build();
            matchDTO.result(resultDTO);
        }

        return matchDTO.build();
    }

}
