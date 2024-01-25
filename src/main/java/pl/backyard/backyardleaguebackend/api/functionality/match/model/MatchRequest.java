package pl.backyard.backyardleaguebackend.api.functionality.match.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckByRegexp;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasAccessToTeam;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MatchRequest {

    @NotNull
    @Size(max = 255)
    private String location;
    @Size(max = 255)
    private String comment;
    @NotNull
    @UserHasAccessToTeam
    private Long challengerId;
    @NotNull
    private Long challengedId;

    @CheckByRegexp(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    @NotNull
    @Schema(description = "Match time in format: yyyy-MM-dd HH:mm:ss")
    private String matchTime;


}
