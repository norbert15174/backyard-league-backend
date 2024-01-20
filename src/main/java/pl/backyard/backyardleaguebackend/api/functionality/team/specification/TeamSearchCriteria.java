package pl.backyard.backyardleaguebackend.api.functionality.team.specification;

import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;

public record TeamSearchCriteria(String name, Long minPoints, Long maxPoints, GameType type) {

}
