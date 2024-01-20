package pl.backyard.backyardleaguebackend.api.functionality.team.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamCreateRequest {

    private String name;
    private GameType type;

}
