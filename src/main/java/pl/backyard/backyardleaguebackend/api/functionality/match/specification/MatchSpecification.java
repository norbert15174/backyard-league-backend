package pl.backyard.backyardleaguebackend.api.functionality.match.specification;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.api.functionality.common.specification.BaseSpecification;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.MatchStatus;

import static org.springframework.data.jpa.domain.Specification.where;

public class MatchSpecification extends BaseSpecification<Match, MatchSearchCriteria> {
    @Override
    public Specification<Match> getFilter(MatchSearchCriteria request) {
        return where(status(request.status()).and(id(request.teamId())));
    }

    private Specification<Match> id(Long id) {
        return (root, query, criteriaBuilder) -> {
            var joinByChallenged = root.join("challenged", JoinType.LEFT).get("id").in(id);
            var joinByChallenger = root.join("challenger", JoinType.LEFT).get("id").in(id);
            return criteriaBuilder.or(joinByChallenged, joinByChallenger);
        };
    }

    private Specification<Match> status(MatchStatus status) {
        return (root, query, criteriaBuilder) -> root.get("status").in(status);
    }

}
