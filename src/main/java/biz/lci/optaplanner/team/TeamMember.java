package biz.lci.optaplanner.team;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamMember {
    protected int id;
    @EqualsAndHashCode.Exclude
    protected String name;
    @EqualsAndHashCode.Exclude
    List<LocalDate> blackoutDates;

    public TeamMember(int id, String name, LocalDate ... blackouts) {
        this.id = id;
        this.name = name;
        this.blackoutDates = Arrays.asList(blackouts);
    }

    public String toString() {
        return name + "[" + id + "]";
    }

    public boolean isBlackoutDate(LocalDate date) {
        return blackoutDates.contains(date);
    }
}
