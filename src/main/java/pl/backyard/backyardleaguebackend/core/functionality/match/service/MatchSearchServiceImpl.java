package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.mapper.MatchMapper;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.crud.MatchQueryService;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
class MatchSearchServiceImpl implements MatchSearchService{

    private final MatchMapper mapper;
    private final MatchQueryService matchQueryService;

    @Override
    public MatchDTO getById(Long id){
        var match = matchQueryService.getByIdWithTeamsAndResult(id);
        return mapper.mapToMatchDTO(match.getChallenger(), match.getChallenged(), match);
    }

}
