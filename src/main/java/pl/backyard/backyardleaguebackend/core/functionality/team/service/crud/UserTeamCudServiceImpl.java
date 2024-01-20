package pl.backyard.backyardleaguebackend.core.functionality.team.service.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.common.service.CudServiceImpl;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeam;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeamId;

@Transactional
@Service
class UserTeamCudServiceImpl extends CudServiceImpl<UserTeamRepository, UserTeam, UserTeamId> implements UserTeamCudService {

    public UserTeamCudServiceImpl(UserTeamRepository repository) {
        super(repository);
    }

}
