package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchRequest;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchOpinionDTO;

public interface MatchService {
    MatchDTO create(MatchRequest request);

    MatchDTO opinion(MatchOpinionDTO dto);
}
