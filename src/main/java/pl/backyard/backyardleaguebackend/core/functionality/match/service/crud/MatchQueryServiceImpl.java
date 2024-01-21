package pl.backyard.backyardleaguebackend.core.functionality.match.service.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

@Service
@Transactional
@AllArgsConstructor
class MatchQueryServiceImpl implements MatchQueryService {

    private final MatchRepository repository;

    @Override
    public Match getByIdWithTeamsAndResult(Long id) {
        return repository.findByIdWithTeamsAndResult(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Match> getAll(Specification<Match> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

}
