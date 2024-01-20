package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;

public interface MatchSearchService {
    MatchDTO getById(Long id);
}
