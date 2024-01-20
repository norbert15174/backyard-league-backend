package pl.backyard.backyardleaguebackend.core.functionality.team.service.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeam;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeamId;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, UserTeamId> {
}
