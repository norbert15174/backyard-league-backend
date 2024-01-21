package pl.backyard.backyardleaguebackend.core.functionality.match.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.common.domain.EntityId;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "match")
public class Match implements EntityId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String comment;
    @Enumerated(EnumType.STRING)
    private GameType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenger_id", nullable = false)
    private Team challenger;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenged_id", nullable = false)
    private Team challenged;
    private LocalDateTime challengedAt = LocalDateTime.now();
    private LocalDateTime matchTime;
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.REQUESTED;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    public void addResult(Result entity) {
        getResult().setMatch(this);
        this.setResult(entity);
    }
}
