package pl.backyard.backyardleaguebackend.core.functionality.match.service.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m " +
            "left join fetch m.challenged cd " +
            "left join fetch m.challenger cr " +
            "left join fetch m.result ")
    Optional<Match> findByIdWithTeamsAndResult(@Param("id") Long id);
}
