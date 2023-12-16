package pl.backyard.backyardleaguebackend.core.functionality.user.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.RegisterDTO;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User build(RegisterDTO dto, String encodedPassword){
        var user = new User();
        user.setName(dto.name());
        user.setLastname(dto.lastname());
        user.setUsername(dto.username());
        user.setPassword(encodedPassword);
        user.setEmail(dto.email());
        return user;
    }

}
