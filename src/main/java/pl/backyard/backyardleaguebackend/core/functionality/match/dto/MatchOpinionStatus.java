package pl.backyard.backyardleaguebackend.core.functionality.match.dto;

import java.util.Objects;
import java.util.Set;

public enum MatchOpinionStatus {

    ACCEPT, REJECT, FINISH;

    public boolean isAcceptOrReject() {
        return Set.of(ACCEPT, REJECT).contains(this);
    }

    public boolean isFinish() {
        return Objects.equals(this, FINISH);
    }

}
