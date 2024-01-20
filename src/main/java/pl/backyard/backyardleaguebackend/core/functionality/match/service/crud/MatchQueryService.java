package pl.backyard.backyardleaguebackend.core.functionality.match.service.crud;

import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

public interface MatchQueryService {
    Match getByIdWithTeamsAndResult(Long id);
}
