package com.jlizarazo.jpmorgan.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Report {

    private final List<Instruction> instructionList;
    private List<Ranking> rankingListIncome,
            rankingListOutcome;

    private List<DailyAmount> dailyIncome,
            dailyOutcome;

    public Report(List<Instruction> instructionList) {
        this.instructionList = instructionList;
    }

    public List<Instruction> getInstructionList() {
        return new ArrayList<>(instructionList);
    }

    public void setRankingListIncome(List<Ranking> rankingListIncome) {
        this.rankingListIncome = rankingListIncome;
    }

    public void setRankingListOutcome(List<Ranking> rankingListOutcome) {
        this.rankingListOutcome = rankingListOutcome;
    }

    public void setDailyIncome(List<DailyAmount> dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public void setDailyOutcome(List<DailyAmount> dailyOutcome) {
        this.dailyOutcome = dailyOutcome;
    }

    @Override
    public String toString() {
        System.out.println("\n[ Entity  | Buy/Sell | AgreedFx | Currency | InstructionDate | SettlementDate |   Units    | Price per unit ]");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        this.instructionList.forEach(System.out::println);
        System.out.println("\n\n\n[ Incoming Operations by Date ]");
        System.out.println("------------------------------");
        this.dailyIncome.forEach(System.out::println);
        System.out.println("\n[   Top Ranking for Income   ]");
        System.out.println("------------------------------");
        this.rankingListIncome.forEach(System.out::println);
        System.out.println("\n\n\n[ Outgoing Operations by Date ]");
        System.out.println("------------------------------");
        this.dailyOutcome.forEach(System.out::println);
        System.out.println("\n[   Top Ranking for Outcome   ]");
        System.out.println("------------------------------");
        this.rankingListOutcome.forEach(System.out::println);
        return "";
    }

}
