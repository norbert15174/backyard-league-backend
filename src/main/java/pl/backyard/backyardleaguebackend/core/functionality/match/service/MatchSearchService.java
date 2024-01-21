package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchBaseDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;

public interface MatchSearchService {
    MatchDTO getById(Long id);

    Page<MatchBaseDTO> getAll(Specification<Match> spec, Pageable pageable);
}
