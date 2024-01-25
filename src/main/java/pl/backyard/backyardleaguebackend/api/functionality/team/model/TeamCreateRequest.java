package pl.backyard.backyardleaguebackend.api.functionality.team.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasNoTeam;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamCreateRequest {

    @Size(max = 255)
    @NotNull
    private String name;

    @UserHasNoTeam
    @NotNull
    private GameType type;

}
