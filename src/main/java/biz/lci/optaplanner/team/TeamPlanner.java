package biz.lci.optaplanner.team;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TeamPlanner {
    private static final LocalDate [] LEAGUE_DATES = {
//        LocalDate.of(2021, Month.MAY, 13),
//        LocalDate.of(2021, Month.MAY, 20),
//        LocalDate.of(2021, Month.MAY, 27),
//        LocalDate.of(2021, Month.JUNE, 3),
//        LocalDate.of(2021, Month.JUNE, 10),
//        LocalDate.of(2021, Month.JUNE, 17),
//        LocalDate.of(2021, Month.JUNE, 24),
        LocalDate.of(2021, Month.JULY, 1),
        LocalDate.of(2021, Month.JULY, 8)
    };
    private static final int TEAM_SIZE = 2;

    public static void main(String [] args) {
        System.out.println("TeamPlanner.main");

        SolverFactory<TeamPlan> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(TeamPlan.class)
                .withEntityClasses(TeamMemberAssignment.class)
                .withEasyScoreCalculatorClass(TeamPlanEasyScoreCalculator.class)
                .withTerminationSpentLimit(Duration.ofSeconds(30)));
        Solver<TeamPlan> solver = solverFactory.buildSolver();

        TeamPlan unsolvedTeamPlan = new TeamPlan();
        List<TeamMember> members = new ArrayList<>();
        members.add(new TeamMember(0, "P0"));
        members.add(new TeamMember(1, "P1"));
        members.add(new TeamMember(2, "P2"));
//        members.add(new TeamMember(3, "P3"));
//        members.add(new TeamMember(4, "P4"));
//        members.add(new TeamMember(5, "P5"));
//        members.add(new TeamMember(6, "P6",
//                LocalDate.of(2021, Month.JUNE, 17),
//                LocalDate.of(2021, Month.JUNE, 24),
//                LocalDate.of(2021, Month.JULY, 1),
//                LocalDate.of(2021, Month.JULY, 15),
//                LocalDate.of(2021, Month.JULY, 22)));
        unsolvedTeamPlan.setAvailableTeamMembers(members);

        List<TeamMemberAssignment> assignments = new ArrayList<>();
        for(LocalDate dt : LEAGUE_DATES) {
            for(int i = 0; i < TEAM_SIZE; i++) {
                assignments.add(new TeamMemberAssignment(null, dt));
            }
        }
        unsolvedTeamPlan.setTeamMemberAssignments(assignments);

        // Solve the problem
        System.out.println("TeamPlanner.main solving...");
        TeamPlan solvedTeamPlan = solver.solve(unsolvedTeamPlan);

        // Display the result
        System.out.println("\nSolved team plan:\n" + solvedTeamPlan.toPlanSummaryString());
//        System.out.println(solvedTeamPlan.toMemberDaysString());

        System.out.println("\nfinal score: " + solvedTeamPlan.getPlanScore());

        System.out.println("\nTeamPlanner.main exiting...");
    }
}
