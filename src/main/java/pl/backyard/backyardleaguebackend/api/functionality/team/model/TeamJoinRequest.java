package pl.backyard.backyardleaguebackend.api.functionality.team.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasNoTeamCode;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class TeamJoinRequest {

    @NotNull
    @UserHasNoTeamCode
    private String code;

}
