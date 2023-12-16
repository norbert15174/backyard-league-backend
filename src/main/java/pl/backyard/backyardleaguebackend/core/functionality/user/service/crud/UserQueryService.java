package pl.backyard.backyardleaguebackend.core.functionality.user.service.crud;


import pl.backyard.backyardleaguebackend.core.functionality.common.domain.CheckUnique;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.util.Optional;

public interface UserQueryService extends CheckUnique {
    Optional<User> getOptByUsernameOrEmail(String username);

    Optional<User> getOptByEmail(String email);

}
