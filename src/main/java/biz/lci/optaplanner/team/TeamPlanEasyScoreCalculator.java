package biz.lci.optaplanner.team;

import biz.lci.optaplanner.cloud.CloudBalance;
import biz.lci.optaplanner.cloud.Computer;
import biz.lci.optaplanner.cloud.Process;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.Arrays;

public class TeamPlanEasyScoreCalculator implements EasyScoreCalculator<TeamPlan, HardSoftScore> {
    /**
     * A very simple implementation. The double loop can easily be removed by using Maps as shown in
     */
    @Override
    public HardSoftScore calculateScore(TeamPlan teamPlan) {
        int hardScore = 0;
        int softScore = 0;

        // HARD: different 4 players for each date
        // HARD: member not schedule on blackout date
        //
        // SOFT: equal # of days for each member
        // SOFT: each member plays with other members at least once
        for(LeagueDate leagueDate : teamPlan.getLeagueDates()) {
            int [] memberArray = new int[teamPlan.getAvailableTeamMembers().size()];
            if(leagueDate.getTeamMember0() != null) {
                memberArray[leagueDate.getTeamMember0().getId()]++;
            } else {
                hardScore -= 10;
            }
            if(leagueDate.getTeamMember1() != null) {
                memberArray[leagueDate.getTeamMember1().getId()]++;
            } else {
                hardScore -= 10;
            }
            if(leagueDate.getTeamMember2() != null) {
                memberArray[leagueDate.getTeamMember2().getId()]++;
            } else {
                hardScore -= 10;
            }
            if(leagueDate.getTeamMember3() != null) {
                memberArray[leagueDate.getTeamMember3().getId()]++;
            } else {
                hardScore -= 10;
            }

            // square the counts > 1 so that multiple doublebookings have higher penalties
            int doubleBookedPenalty = Arrays.stream(memberArray).filter(i -> i > 1).map(i -> i*i).sum();

            hardScore -= doubleBookedPenalty;
        }

//        for (Computer computer : teamPlan.getComputerList()) {
//            int cpuPowerUsage = 0;
//            int memoryUsage = 0;
//            int networkBandwidthUsage = 0;
//            boolean used = false;
//
//            // Calculate usage
//            for (Process process : teamPlan.getProcessList()) {
//                if (computer.equals(process.getComputer())) {
//                    cpuPowerUsage += process.getRequiredCpuPower();
//                    memoryUsage += process.getRequiredMemory();
//                    networkBandwidthUsage += process.getRequiredNetworkBandwidth();
//                    used = true;
//                }
//            }
//
//            // Hard constraints
//            int cpuPowerAvailable = computer.getCpuPower() - cpuPowerUsage;
//            if (cpuPowerAvailable < 0) {
//                hardScore += cpuPowerAvailable;
//            }
//            int memoryAvailable = computer.getMemory() - memoryUsage;
//            if (memoryAvailable < 0) {
//                hardScore += memoryAvailable;
//            }
//            int networkBandwidthAvailable = computer.getNetworkBandwidth() - networkBandwidthUsage;
//            if (networkBandwidthAvailable < 0) {
//                hardScore += networkBandwidthAvailable;
//            }
//
//            // Soft constraints
//            if (used) {
//                softScore -= computer.getCost();
//            }
//        }
        return HardSoftScore.of(hardScore, softScore);
    }
}
