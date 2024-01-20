package pl.backyard.backyardleaguebackend.core.functionality.match.service.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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

}
