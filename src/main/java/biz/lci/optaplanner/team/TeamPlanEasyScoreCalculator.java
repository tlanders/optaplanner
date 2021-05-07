package biz.lci.optaplanner.team;

import lombok.extern.slf4j.Slf4j;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.Arrays;

@Slf4j
public class TeamPlanEasyScoreCalculator implements EasyScoreCalculator<TeamPlan, HardSoftScore> {
    /**
     * A very simple implementation. The double loop can easily be removed by using Maps as shown in
     */
    @Override
    public HardSoftScore calculateScore(TeamPlan teamPlan) {
        int hardScore = 0;
        int softScore = 0;

        // HARD: different 4 players for each date
        // HARD: member not schedule on blackout date
        int [] memberLeagueDaysArray = new int[teamPlan.getAvailableTeamMembers().size()];
        for(LeagueDate leagueDate : teamPlan.getLeagueDates()) {
            int [] memberArray = new int[teamPlan.getAvailableTeamMembers().size()];
            if(leagueDate.getTeamMember0() != null) {
                memberLeagueDaysArray[leagueDate.getTeamMember0().getId()]++;
                memberArray[leagueDate.getTeamMember0().getId()]++;
            } else {
                hardScore -= 10;
            }
            if(leagueDate.getTeamMember1() != null) {
                memberLeagueDaysArray[leagueDate.getTeamMember1().getId()]++;
                memberArray[leagueDate.getTeamMember1().getId()]++;
            } else {
                hardScore -= 10;
            }
            if(leagueDate.getTeamMember2() != null) {
                memberLeagueDaysArray[leagueDate.getTeamMember2().getId()]++;
                memberArray[leagueDate.getTeamMember2().getId()]++;
            } else {
                hardScore -= 10;
            }
            if(leagueDate.getTeamMember3() != null) {
                memberLeagueDaysArray[leagueDate.getTeamMember3().getId()]++;
                memberArray[leagueDate.getTeamMember3().getId()]++;
            } else {
                hardScore -= 10;
            }

            // square the counts > 1 so that multiple doublebookings have higher penalties
            int doubleBookedPenalty = Arrays.stream(memberArray).filter(i -> i > 1).map(i -> i*i).sum();

            hardScore -= doubleBookedPenalty;
        }

        // SOFT: equal # of days for each member
        // SOFT: each member plays with other members at least once
        int maxDaysPlayed = Arrays.stream(memberLeagueDaysArray).max().getAsInt();
        int minDaysPlayed = Arrays.stream(memberLeagueDaysArray).min().getAsInt();

        softScore -= (maxDaysPlayed - minDaysPlayed);

        log.info("Potential solution - score: [{},{}] \n{}", hardScore, softScore, teamPlan.toPlanSummaryString());

        return HardSoftScore.of(hardScore, softScore);
    }
}
