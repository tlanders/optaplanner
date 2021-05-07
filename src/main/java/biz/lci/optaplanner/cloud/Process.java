package biz.lci.optaplanner.cloud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PlanningEntity
public class Process {
    private int requiredCpuPower;
    private int requiredMemory;
    private int requiredNetworkBandwidth;
    private int id;

    private Computer computer;

    @PlanningVariable(valueRangeProviderRefs = {"computerRange"})
    public Computer getComputer() {
        return computer;
    }
    public String getLabel() {
        return "Process " + id;
    }
}
