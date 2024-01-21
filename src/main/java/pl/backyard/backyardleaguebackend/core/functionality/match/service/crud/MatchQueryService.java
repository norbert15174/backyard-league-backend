package pl.backyard.backyardleaguebackend.core.functionality.match.service.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

public interface MatchQueryService {
    Match getByIdWithTeamsAndResult(Long id);

    Page<Match> getAll(Specification<Match> spec, Pageable pageable);
}
