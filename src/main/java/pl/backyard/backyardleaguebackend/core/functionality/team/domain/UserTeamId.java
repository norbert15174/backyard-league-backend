package pl.backyard.backyardleaguebackend.core.functionality.team.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTeamId implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "team_id")
    private Long teamId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTeamId that = (UserTeamId) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getTeamId(), that.getTeamId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getTeamId());
    }

}
