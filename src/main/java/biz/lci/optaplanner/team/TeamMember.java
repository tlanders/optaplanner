package biz.lci.optaplanner.team;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamMember {
    protected int id;
    protected String name;
    List<LocalDate> blackoutDates;

    public TeamMember(int id, String name, LocalDate ... blackouts) {
        this.name = name;
        this.blackoutDates = Arrays.asList(blackouts);
    }

    public String toString() {
        return name + "[" + id + "]";
    }
}
