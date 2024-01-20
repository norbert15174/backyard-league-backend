package pl.backyard.backyardleaguebackend.core.functionality.team.service.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamCodeDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {
    Optional<Team> findTeamByCode(String code);

    @Query("select new pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamCodeDTO(t.code) from Team t where t.id = :id")
    Optional<TeamCodeDTO> findCodeById(@Param("id") Long id);

    @Query("select t from Team t " +
            "left join t.users ut " +
            "left join ut.user " +
            "where t.id = :id")
    Optional<Team> findByIdWithMembers(@Param("id") Long id);

    @Query("SELECT COUNT(t) FROM Team t WHERE t.type = :type and t.points > :points OR (t.points = :points AND t.id < :teamId)")
    long countTeamsWithMorePoints(@Param("points") Long points, @Param("teamId") Long teamId, @Param("type") GameType type);

}
