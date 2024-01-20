package pl.backyard.backyardleaguebackend.core.functionality.team.service.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.common.service.CudServiceImpl;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;

@Transactional
@Service
class TeamCudServiceImpl extends CudServiceImpl<TeamRepository, Team, Long> implements TeamCudService {

    public TeamCudServiceImpl(TeamRepository repository) {
        super(repository);
    }

}
