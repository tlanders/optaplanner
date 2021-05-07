package biz.lci.optaplanner.team;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalDate;

@Data
@PlanningEntity
@NoArgsConstructor
public class LeagueDate {
    protected LocalDate date;

    public LeagueDate(LocalDate date) {
        this.date = date;
    }

    @PlanningVariable(valueRangeProviderRefs = {"teamMembers" })
    @HashCodeExclude
    protected TeamMember teamMember0;
//    @PlanningVariable(valueRangeProviderRefs = {"teamMembers" })
//    @HashCodeExclude
//    protected TeamMember teamMember1;
//    @PlanningVariable(valueRangeProviderRefs = {"teamMembers" })
//    @HashCodeExclude
//    protected TeamMember teamMember2;
//    @PlanningVariable(valueRangeProviderRefs = {"teamMembers" })
//    @HashCodeExclude
//    protected TeamMember teamMember3;

    public String toString() {
        return "League Day: " + date.toString()
                + "\n  0:" + teamMember0;
//                + "\n  1:" + teamMember1
//                + "\n  2:" + teamMember2
//                + "\n  3:" + teamMember3;
    }
}
