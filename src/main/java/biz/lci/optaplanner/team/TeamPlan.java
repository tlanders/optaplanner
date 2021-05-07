package biz.lci.optaplanner.team;

import lombok.Data;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@Data
@PlanningSolution
public class TeamPlan {
    @ValueRangeProvider(id = "teamMembers")
    @ProblemFactCollectionProperty
    protected List<TeamMember> availableTeamMembers;

    @PlanningEntityCollectionProperty
    protected List<LeagueDate> leagueDates;

    @PlanningScore
    protected HardSoftScore planScore;

    public String toPlanSummaryString() {
        StringBuilder displayString = new StringBuilder();
        for (LeagueDate leagueDate : getLeagueDates()) {
            displayString.append(leagueDate).append("\n");
        }
        return displayString.toString();
    }

    public String toMemberDaysString() {
        int[] memberDays = getMemberDays();

        String str = "Days Played By Member:\n";
        for(int id = 0; id < memberDays.length; id++) {
            str += ("  " + id + ": " + memberDays[id] + '\n');
        }
        return str;
    }

    private int[] getMemberDays() {
        int [] memberDays = new int[availableTeamMembers.size()];
        for(LeagueDate date : getLeagueDates()) {
            if(date.getTeamMember0() != null) {
                memberDays[date.getTeamMember0().getId()]++;
            }
            if(date.getTeamMember1() != null) {
                memberDays[date.getTeamMember1().getId()]++;
            }
            if(date.getTeamMember2() != null) {
                memberDays[date.getTeamMember2().getId()]++;
            }
            if(date.getTeamMember3() != null) {
                memberDays[date.getTeamMember3().getId()]++;
            }
        }
        return memberDays;
    }
}
