package pl.backyard.backyardleaguebackend.core.functionality.result.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.Result;
import pl.backyard.backyardleaguebackend.core.functionality.result.domain.ResultStatus;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointHelper {

    private static final Long BASE_CALC_POINT = 50L;
    private static final Long BASE_CALC_POINT_FOR_DRAW = 25L;

    public static void calcChallengerPoints(Result result, Team challenger, Team challenged) {
        var ratioChallenger = ((double) (challenger.getPoints())) / ((double) (challenged.getPoints()));
        var ratioChallenged = ((double) (challenged.getPoints())) / ((double) (challenger.getPoints()));
        if (Objects.equals(result.getChallengedScore(), result.getChallengerScore())) {
            var points = calcPoints(ratioChallenger, ratioChallenged, BASE_CALC_POINT_FOR_DRAW);
            challenger.addPoints(points);
            challenged.addPoints(points);
            return;
        }

        var points = calcPoints(ratioChallenger, ratioChallenged, BASE_CALC_POINT);
        if (Objects.equals(result.getStatus(), ResultStatus.CHALLENGER_WON)) {
            challenger.addPoints(points);
            challenged.removePoints(points);
            return;
        }

        challenger.removePoints(points);
        challenged.addPoints(points);
    }

    private static Long calcPoints(double ratio1, double ratio2, Long points) {
        if (ratio1 == ratio2) {
            return points;
        }

        var points1 = Math.round(ratio1 * points);
        var points2 = Math.round(ratio2 * points);
        return Math.abs(points1 - points2);
    }

}
