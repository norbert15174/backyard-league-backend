package pl.backyard.backyardleaguebackend.core.functionality.team.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.backyard.backyardleaguebackend.core.functionality.common.domain.EntityId;
import pl.backyard.backyardleaguebackend.core.functionality.match.domain.Match;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "team")
public class Team implements EntityId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private GameType type;
    private Long points = 1000L;
    private String code = UUID.randomUUID().toString();
    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "team")
    private Set<UserTeam> users;
    @OneToMany(mappedBy = "challenger", fetch = FetchType.LAZY)
    private Set<Match> challengerMatches = new HashSet<>();
    @OneToMany(mappedBy = "challenged", fetch = FetchType.LAZY)
    private Set<Match> challengedMatches = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.nonNull(getId()) && Objects.equals(getId(), team.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
