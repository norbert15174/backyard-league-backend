package pl.backyard.backyardleaguebackend.core.functionality.team.service.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamCodeDTO;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
class TeamQueryServiceImpl implements TeamQueryService {

    private final TeamRepository repository;

    @Override
    public Team getByCode(String code) {
        return repository.findTeamByCode(code)
                .orElseThrow(EntityNotFoundException::new);

    }

    @Override
    public TeamCodeDTO getCodeById(Long id) {
        return repository.findCodeById(id)
                .orElseThrow(EntityNotFoundException::new);

    }

    @Override
    public Team getByIdWithMembers(Long id) {
        return repository.findByIdWithMembers(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public long countTeamsWithMorePoints(Team team) {
        return repository.countTeamsWithMorePoints(team.getPoints(), team.getId(), team.getType());
    }

    @Override
    public Page<Team> getAll(Specification<Team> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

}
