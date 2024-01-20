package pl.backyard.backyardleaguebackend.core.functionality.user.service.crud;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> getOptByUsername(String username);

    User getByUsername(String username);

    User getById(Long userId);

    Page<User> getAll(Specification<User> filter, Pageable pageable);

    User getByIdWithTeams(Long id);
}
