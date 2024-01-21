package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchRequest;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.MatchStatus;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchOpinionDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchOpinionStatus;
import pl.backyard.backyardleaguebackend.core.functionality.match.mapper.MatchMapper;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.crud.MatchCudService;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.crud.MatchQueryService;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.ResultStatus;
import pl.backyard.backyardleaguebackend.core.functionality.result.dto.ResultOpinionDTO;
import pl.backyard.backyardleaguebackend.core.functionality.result.helper.PointHelper;
import pl.backyard.backyardleaguebackend.core.functionality.result.service.ResultCudService;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamCudService;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
class MatchServiceImpl implements MatchService {

    private final TeamQueryService teamQueryService;
    private final TeamCudService teamCudService;
    private final MatchCudService matchCudService;
    private final MatchQueryService matchQueryService;
    private final ResultCudService resultCudService;
    private final MatchMapper mapper;

    @Override
    public MatchDTO create(MatchRequest request) {
        var challenger = teamQueryService.getByIdWithMembers(request.getChallengerId());
        var challenged = teamQueryService.getByIdWithMembers(request.getChallengedId());
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var parsedDate = LocalDateTime.parse(request.getMatchTime(), formatter);

        var match = new Match();
        match.setMatchTime(parsedDate);
        match.setComment(request.getComment());
        match.setLocation(request.getLocation());
        match.setChallenger(challenger);
        match.setChallenged(challenged);
        match.setType(challenger.getType());
        var created = matchCudService.create(match);
        return mapper.mapToMatchDTO(challenger, challenged, created);
    }

    @Override
    public MatchDTO opinion(MatchOpinionDTO dto) {
        var match = matchQueryService.getByIdWithTeamsAndResult(dto.id());
        Team challenger = match.getChallenger();
        Team challenged = match.getChallenged();
        if (dto.status().isAcceptOrReject()) {
            match.setStatus(Objects.equals(dto.status(), MatchOpinionStatus.ACCEPT) ? MatchStatus.IN_PROGRESS : MatchStatus.REJECTED);
            matchCudService.update(match);
            return mapper.mapToMatchDTO(challenger, challenged, match);
        }

        var result = dto.result();
        var entity = new Result();
        entity.setChallengerScore(result.challengerScore());
        entity.setChallengedScore(result.challengedScore());

        var resultStatus = getStatus(result);
        entity.setStatus(resultStatus);
        match.addResult(entity);
        var created = resultCudService.create(entity);

        match.setStatus(MatchStatus.FINISHED);
        var updated = matchCudService.update(match);

        PointHelper.calcChallengerPoints(created, challenger, challenged);
        var updatedChallenger = teamCudService.update(challenger);
        var updatedChallenged = teamCudService.update(challenged);

        return mapper.mapToMatchDTO(updatedChallenger, updatedChallenged, updated);
    }

    private ResultStatus getStatus(ResultOpinionDTO result) {
        if (Objects.equals(result.challengedScore(), result.challengerScore())) {
            return ResultStatus.DRAW;
        }

        return result.challengedScore() > result.challengerScore()
                ? ResultStatus.CHALLENGED_WON
                : ResultStatus.CHALLENGER_WON;
    }


}
