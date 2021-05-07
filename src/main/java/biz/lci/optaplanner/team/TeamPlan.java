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

    public String toDisplayString() {
        StringBuilder displayString = new StringBuilder();
        for (LeagueDate leagueDate : getLeagueDates()) {
            displayString.append(leagueDate).append("\n");
        }
        return displayString.toString();
    }
}
