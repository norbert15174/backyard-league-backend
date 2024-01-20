package pl.backyard.backyardleaguebackend.core.functionality.match.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="challenger_id", nullable=false)
    private Team challenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="challenged_id", nullable=false)
    private Team challenged;

    private LocalDate challengedAt = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.REQUESTED;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="result_id")
    private Result result;

}
