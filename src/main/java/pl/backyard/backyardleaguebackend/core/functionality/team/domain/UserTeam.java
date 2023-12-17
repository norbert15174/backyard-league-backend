package pl.backyard.backyardleaguebackend.core.functionality.team.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users_team")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "user", column = @Column(name = "user_id")),
            @AttributeOverride(name = "team", column = @Column(name = "team_id")),
    })
    private UserTeamId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    private TeamRoleType role = TeamRoleType.TEAMMATE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTeam userTeam = (UserTeam) o;
        return Objects.equals(getId(), userTeam.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
