package biz.lci.optaplanner.team;

import lombok.Data;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@PlanningSolution
public class TeamPlan {
    @ValueRangeProvider(id = "teamMembers")
    @ProblemFactCollectionProperty
    protected List<TeamMember> availableTeamMembers;

    @PlanningEntityCollectionProperty
    protected List<TeamMemberAssignment> teamMemberAssignments;

    @PlanningScore
    protected HardSoftScore planScore;

    public String toPlanSummaryString() {
        StringBuilder displayString = new StringBuilder();
        Map<LocalDate, List<TeamMemberAssignment>> dateAssignmentMap =  teamMemberAssignments.stream()
                .collect(Collectors.groupingBy(TeamMemberAssignment::getLeagueDate));

        for(LocalDate date : dateAssignmentMap.keySet()) {
            displayString.append(date).append('\n');
            List<TeamMember> members = dateAssignmentMap.get(date).stream()
                    .map(TeamMemberAssignment::getTeamMember)
                    .sorted(Comparator.comparingInt(TeamMember::getId))
                    .collect(Collectors.toList());

            for(TeamMember member : members) {
                displayString.append(member.getName()).append('\n');
            }
        }

        return displayString.append('\n').toString();
    }

//    public String toMemberDaysString() {
//        int[] memberDays = getMemberDays();
//
//        String str = "Days Played By Member:\n";
//        for(int id = 0; id < memberDays.length; id++) {
//            str += ("  " + id + ": " + memberDays[id] + '\n');
//        }
//        return str;
//    }

//    private int[] getMemberDays() {
//        int [] memberDays = new int[availableTeamMembers.size()];
//        for(LeagueDate date : getTeamMemberAssignments()) {
//            if(date.getTeamMember0() != null) {
//                memberDays[date.getTeamMember0().getId()]++;
//            }
//            if(date.getTeamMember1() != null) {
//                memberDays[date.getTeamMember1().getId()]++;
//            }
//            if(date.getTeamMember2() != null) {
//                memberDays[date.getTeamMember2().getId()]++;
//            }
//            if(date.getTeamMember3() != null) {
//                memberDays[date.getTeamMember3().getId()]++;
//            }
//        }
//        return memberDays;
//    }
}
