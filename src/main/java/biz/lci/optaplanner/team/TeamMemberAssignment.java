package biz.lci.optaplanner.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalDate;

@PlanningEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberAssignment {
    @PlanningVariable(valueRangeProviderRefs = {"teamMembers" })
    private TeamMember teamMember;
    private LocalDate leagueDate;
}
