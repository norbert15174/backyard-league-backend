package pl.backyard.backyardleaguebackend.api.functionality.match.specification;

import pl.backyard.backyardleaguebackend.core.functionality.match.domain.MatchStatus;

public record MatchSearchCriteria(Long teamId, MatchStatus status) {

}
