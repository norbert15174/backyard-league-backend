package pl.backyard.backyardleaguebackend.api.functionality.match.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MatchRequest {

    private String location;
    private String comment;
    private Long challengerId;
    private Long challengedId;
    @Schema(description = "Match time in format: yyyy-MM-dd HH:mm:ss")
    private String matchTime;


}
