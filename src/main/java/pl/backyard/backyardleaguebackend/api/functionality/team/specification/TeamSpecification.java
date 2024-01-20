package pl.backyard.backyardleaguebackend.api.functionality.team.specification;

import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.api.functionality.common.specification.BaseSpecification;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;

import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

public class TeamSpecification extends BaseSpecification<Team, TeamSearchCriteria> {
    @Override
    public Specification<Team> getFilter(TeamSearchCriteria request) {
        return where(type(request.type())
                .and(name(request.name()))
                .and(minPoint(request.minPoints()))
                .and(maxPoint(request.minPoints())));
    }

    private Specification<Team> minPoint(Long minPoints) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(minPoints)) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("points"), minPoints);
        };
    }

    private Specification<Team> maxPoint(Long maxPoints) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(maxPoints)) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("points"), maxPoints);
        };
    }

    private Specification<Team> type(GameType type) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.isNull(type)) {
                return null;
            }
            return root.get("type").in(type);
        };
    }

    private Specification<Team> name(String name) {
        return (root, query, criteriaBuilder) -> {
            if (Strings.isNullOrEmpty(name)) {
                return null;
            }
            return criteriaBuilder.like(root.get("name"), containsLowerCase(name));
        };
    }


}
