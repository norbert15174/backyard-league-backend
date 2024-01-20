package pl.backyard.backyardleaguebackend.core.functionality.team.dto;


import lombok.*;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.TeamRoleType;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class TeamInformationDTO {

    private final Long id;
    private final String name;
    private final GameType type;
    private final Long points;
    private final Long rank;
    @Setter
    private List<Members> members;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Members {
        private Long id;
        private String username;
        private String firstname;
        private String lastname;
        private TeamRoleType role;
    }

}
