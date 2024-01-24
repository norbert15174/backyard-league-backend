package pl.backyard.backyardleaguebackend.core.functionality.result.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.ResultStatus;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointHelper {

    private static final Long BASE_CALC_POINT = 100L;
    private static final Long BASE_CALC_POINT_FOR_DRAW = 50L;

    public static void calcChallengerPoints(Result result, Team challenger, Team challenged) {
        var ratioChallenger = ((double) (challenger.getPoints())) / ((double) (challenged.getPoints()));
        var ratioChallenged = ((double) (challenged.getPoints())) / ((double) (challenger.getPoints()));

        if (Objects.equals(result.getChallengedScore(), result.getChallengerScore())) {
            challenger.addPoints(Math.round(ratioChallenger * BASE_CALC_POINT_FOR_DRAW));
            challenged.addPoints(Math.round(ratioChallenged * BASE_CALC_POINT_FOR_DRAW));
            return;
        }



        if (Objects.equals(result.getStatus(), ResultStatus.CHALLENGER_WON)) {
            var ratioForWinner = challenger.getPoints() > challenged.getPoints()
                    ? Math.min(ratioChallenger, ratioChallenged)
                    : Math.max(ratioChallenger, ratioChallenged);
            challenger.addPoints(calcPoints(ratioForWinner, BASE_CALC_POINT));
            challenged.removePoints(calcPoints(ratioChallenged, BASE_CALC_POINT));
            return;
        }

        var ratioForWinner = challenged.getPoints() > challenger.getPoints()
                ? Math.min(ratioChallenger, ratioChallenged)
                : Math.max(ratioChallenger, ratioChallenged);
        challenger.removePoints(calcPoints(ratioChallenger, BASE_CALC_POINT));
        challenged.addPoints(calcPoints(ratioForWinner, BASE_CALC_POINT));
    }

    private static long calcPoints(double winnerRatio, Long points) {
        return Math.round(winnerRatio * points);
    }

}
