package pl.backyard.backyardleaguebackend.core.functionality.result.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
