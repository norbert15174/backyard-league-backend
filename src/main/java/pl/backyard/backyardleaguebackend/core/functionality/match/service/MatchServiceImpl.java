package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchRequest;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.mapper.MatchMapper;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.crud.MatchCudService;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;

@Service
@Transactional
@AllArgsConstructor
class MatchServiceImpl implements MatchService {

    private final TeamQueryService teamQueryService;
    private final MatchCudService matchCudService;
    private final MatchMapper mapper;

    @Override
    public MatchDTO create(MatchRequest request) {
        var challenger = teamQueryService.getByIdWithMembers(request.getChallengerId());
        var challenged = teamQueryService.getByIdWithMembers(request.getChallengedId());

        var match = new Match();
        match.setMatchTime(request.getMatchTime());
        match.setComment(request.getComment());
        match.setLocation(request.getLocation());
        match.setChallenger(challenger);
        match.setChallenged(challenged);
        match.setType(challenger.getType());
        var created = matchCudService.create(match);
        return mapper.mapToMatchDTO(challenger, challenged, created);
    }


}
