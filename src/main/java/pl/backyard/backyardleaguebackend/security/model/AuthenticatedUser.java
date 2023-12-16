package pl.backyard.backyardleaguebackend.security.model;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import static pl.backyard.backyardleaguebackend.security.helper.UserAuthorityHelper.getUserAuthorities;


public class AuthenticatedUser extends User {

    @Getter
    private final pl.backyard.backyardleaguebackend.core.functionality.user.domain.User user;

    public AuthenticatedUser(pl.backyard.backyardleaguebackend.core.functionality.user.domain.User user) {
        super(user.getUsername(), user.getPassword(), getUserAuthorities(user));
        this.user = user;
    }

}
