package pl.backyard.backyardleaguebackend.core.functionality.result;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer challengerScore;
    private Integer challengedScore;
    @Enumerated(EnumType.STRING)
    private ResultStatus status;
    @OneToOne(mappedBy = "result")
    private Match match;


}
