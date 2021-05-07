package biz.lci.optaplanner.cloud;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CloudExample {
    public static void main(String [] args) {
        System.out.println("CloudExample.main");

        // Build the Solver
//        SolverFactory<CloudBalance> solverFactory = SolverFactory.createFromXmlResource(
//                "org/optaplanner/examples/cloudbalancing/solver/cloudBalancingSolverConfig.xml");
//        Solver<CloudBalance> solver = solverFactory.buildSolver();

        SolverFactory<CloudBalance> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(CloudBalance.class)
                .withEntityClasses(Process.class)
                .withEasyScoreCalculatorClass(CloudBalancingEasyScoreCalculator.class)
                .withTerminationSpentLimit(Duration.ofSeconds(30)));
        Solver<CloudBalance> solver = solverFactory.buildSolver();

        // Load a problem with 400 computers and 1200 processes
        CloudBalance unsolvedCloudBalance = new CloudBalance();
        List<Computer> clist = new ArrayList<>();
        clist.add(new Computer(2, 200, 200, 300, 0));
        clist.add(new Computer(3, 200, 100, 400, 1));
        clist.add(new Computer(4, 300, 400, 500, 2));
        unsolvedCloudBalance.setComputerList(clist);

        List<Process> plist = new ArrayList<>();
        plist.add(new Process(2, 200, 100, 0, null));
        plist.add(new Process(4, 300, 300, 1, null));
        plist.add(new Process(1, 50, 50, 2, null));
        plist.add(new Process(2, 50, 50, 3, null));
        unsolvedCloudBalance.setProcessList(plist);;

        // Solve the problem
        System.out.println("CloudExample.main solving...");
        CloudBalance solvedCloudBalance = solver.solve(unsolvedCloudBalance);

        // Display the result
        System.out.println("\nSolved cloudBalance with 3 computers and 4 processes:\n"
                + toDisplayString(solvedCloudBalance));

        System.out.println("\nfinal score: " + solvedCloudBalance.getScore());

        System.out.println("\nCloudExample.main exiting...");
    }

    public static String toDisplayString(CloudBalance cloudBalance) {
        StringBuilder displayString = new StringBuilder();
        for (Process process : cloudBalance.getProcessList()) {
            Computer computer = process.getComputer();
            displayString.append("  ").append(process.getLabel()).append(" -> ")
                    .append(computer == null ? null : computer.getLabel()).append("\n");
        }
        return displayString.toString();
    }
}
