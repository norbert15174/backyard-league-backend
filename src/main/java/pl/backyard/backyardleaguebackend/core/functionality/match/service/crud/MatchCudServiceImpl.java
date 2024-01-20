package pl.backyard.backyardleaguebackend.core.functionality.match.service.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.common.service.CudServiceImpl;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

@Transactional
@Service
class MatchCudServiceImpl extends CudServiceImpl<MatchRepository, Match, Long> implements MatchCudService {

    public MatchCudServiceImpl(MatchRepository repository) {
        super(repository);
    }

}
