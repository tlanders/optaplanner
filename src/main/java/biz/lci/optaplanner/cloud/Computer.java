package biz.lci.optaplanner.cloud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Computer {
    private int cpuPower;
    private int memory;
    private int networkBandwidth;
    private int cost;
    private int id;

    public String getLabel() { return "Computer" + id;}
}
