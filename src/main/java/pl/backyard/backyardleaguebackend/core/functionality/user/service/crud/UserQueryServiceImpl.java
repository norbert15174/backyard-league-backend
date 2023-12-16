package pl.backyard.backyardleaguebackend.core.functionality.user.service.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.util.Objects;
import java.util.Optional;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getOptByUsernameOrEmail(String username) {
        return userRepository.findByUsernameOrEmail(username.toLowerCase());
    }

    @Override
    public Optional<User> getOptByEmail(String email) {
        return userRepository.findByEmail(email.toLowerCase());
    }

    @Override
    public boolean isNameExist(String name, Optional<Long> idOpt) {
        var userOpt = getOptByUsernameOrEmail(name);
        return idOpt
                .map(aLong -> userOpt.map(user -> !Objects.equals(user.getId(), aLong))
                        .orElse(false))
                .orElseGet(userOpt::isPresent);
    }

    @Override
    public boolean isEmailExist(String name, Optional<Long> idOpt) {
        var userOpt = getOptByEmail(name);
        return idOpt
                .map(aLong -> userOpt.map(user -> !Objects.equals(user.getId(), aLong))
                        .orElse(false))
                .orElseGet(userOpt::isPresent);
    }
}
