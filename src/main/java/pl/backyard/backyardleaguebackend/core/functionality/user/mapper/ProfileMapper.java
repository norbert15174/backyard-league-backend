package pl.backyard.backyardleaguebackend.core.functionality.user.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamBaseDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.ProfileDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.UserDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;
import pl.backyard.backyardleaguebackend.security.functionality.user.context.UserContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileMapper {

    public static ProfileDTO map(List<TeamBaseDTO> dtos, User user) {
        return ProfileDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .teams(dtos)
                .build();
    }

    public static List<UserDTO> map(Page<User> users) {
        return users.stream().map(user ->
                UserDTO.builder()
                        .fullName(user.getFullName())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .id(user.getId())
                        .build()
        ).collect(Collectors.toList());
    }
}
