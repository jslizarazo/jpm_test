package com.jlizarazo.jpmorgan.service;

import com.jlizarazo.jpmorgan.model.DailyAmount;
import com.jlizarazo.jpmorgan.model.Entity;
import com.jlizarazo.jpmorgan.model.Instruction;
import com.jlizarazo.jpmorgan.model.Operation;
import com.jlizarazo.jpmorgan.model.Ranking;
import com.jlizarazo.jpmorgan.model.Report;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements Reportable {

    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);

    private Report report;

    @Override
    public List<DailyAmount> calculateByOperation(Operation operation) {
        LOG.info(" = = = Calculating the daily amount for " + operation + " operations = = = ");
        BigDecimal totalAmount = new BigDecimal(BigInteger.ZERO);
        Map<String, BigDecimal> tempMap = new HashMap<>();
        List<DailyAmount> dailyAmountList = new ArrayList<>();

        // We loop through all the instructions to get the daily amount
        for (Instruction ri : report.getInstructionList()) {
            // We set the total amount for that specific instruction
            ri.setTotalTrade(calculateTradeAmount(ri));
            if (ri.getOperation() == operation) {
                totalAmount = totalAmount.add(ri.getTotalTrade());
                String date = ri.getSettlementDate().toString();
                // We check if the map contains the date, otherwise we add the date and total amount
                if (!tempMap.containsKey(date)) {
                    tempMap.put(date, ri.getTotalTrade());
                } else {
                    // If the map contains the actual date, we get it, sum the current amount, 
                    // remove it from the map and add the date and the new total amount
                    BigDecimal amount = tempMap.get(date);
                    tempMap.remove(date);
                    amount = amount.add(ri.getTotalTrade());
                    tempMap.put(date, amount);
                }
            }
        }

        // We create a list with the contents of the map
        tempMap.forEach((t, u) -> {
            DailyAmount dailyAmount = new DailyAmount(LocalDate.parse(t), u);
            dailyAmountList.add(dailyAmount);
        });

        // We order the list according to the date
        dailyAmountList.sort(Comparator.comparing(daily -> daily.getDay()));

        return dailyAmountList;
    }

    @Override
    public List<Ranking> getRankingByOperation(Operation operation) {
        LOG.info(" = = = Getting the ranking for " + operation + " operations = = = ");
        List<Instruction> instructionList = report.getInstructionList();
        List<Ranking> rankList = new ArrayList<>();
        
        // We loop through the entities to assign the total amount to each one according to the report
        for (Entity ent : Entity.values()) {
            BigDecimal amount = new BigDecimal(BigInteger.ZERO);
            for (Instruction inst : instructionList) {
                // We chech for the entity and if the operation are the same
                if (operation == inst.getOperation() && ent == inst.getEntity()) {
                    // Safety check in case the total amount of the instruction was not set already
                    if (inst.getTotalTrade() != null) {
                        amount = amount.add(inst.getTotalTrade());
                    } else {
                        inst.setTotalTrade(calculateTradeAmount(inst));
                        amount = amount.add(inst.getTotalTrade());
                    }

                }
            }
            // We create a record with the entity and the total amount for that entity
            Ranking rank = new Ranking(ent, amount);
            rankList.add(rank);
        }
        
        //We reverse order the list accordingly to the amount, so the first in the list will be always the highest ranking
        rankList.sort(Comparator.comparing(instruction -> instruction.getTotalAmount()));
        Collections.reverse(rankList);

        return rankList;
    }

    @Override
    public void checkSettlementDate() {
        LOG.info(" = = = Checking settlement dates = = = ");
        List<Instruction> instructionList = report.getInstructionList();

        for (Instruction instruction : instructionList) {
            LocalDate date = instruction.getSettlementDate();

            switch (instruction.getCurrency()) {
                case AED:
                case SAR:
                    // We check if the date is between Sunday and and Thursday
                    if (!(date.getDayOfWeek().getValue() == 7
                            || (date.getDayOfWeek().getValue() >= 1 && date.getDayOfWeek().getValue() <= 4))) {
                        // If the day is not within the week, we check in which day of the weekend is and add days accordingly
                        // Is not elegant, but it works   =)
                        LOG.info(" = = = Found instruction with " + instruction.getCurrency().getName() + " = = = ");
                        LOG.info(" = = = Weekend found in " + date + ", moving date to next working day = = = ");
                        if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                            instruction.setSettlementDate(date.plusDays(2));
                        } else {
                            instruction.setSettlementDate(date.plusDays(1));
                        }
                        LOG.info(" = = = New date is " + instruction.getSettlementDate() + " = = = ");
                    }
                    break;

                // We do the same procedure for the normal week
                default:
                    if (!(date.getDayOfWeek().getValue() >= 1 && date.getDayOfWeek().getValue() <= 5)) {
                        LOG.info(" = = = Weekend found in " + date + ", moving date to next working day = = = ");
                        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                            instruction.setSettlementDate(date.plusDays(2));
                        } else {
                            instruction.setSettlementDate(date.plusDays(1));
                        }
                        LOG.info(" = = = New date is " + instruction.getSettlementDate() + " = = = ");
                    }

            }
        }
    }

    @Override
    public void createReport(List<Instruction> instructionList) {
        LOG.info(" = = = Creating report = = = ");
        report = new Report(instructionList);

        // We print the first data as a reference for the original dates
        LOG.info(" = = = Printing original data = = = ");
        System.out.println("\n[ Entity  | Buy/Sell | AgreedFx | Currency | InstructionDate | SettlementDate |   Units    | Price per unit ]");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        report.getInstructionList().forEach(System.out::println);
        
        // We check for weekends before getting the daily amounts
        checkSettlementDate();

        // We set the daily amounts and the rankings with the new dates
        report.setDailyIncome(this.calculateByOperation(Operation.SELL));
        report.setDailyOutcome(this.calculateByOperation(Operation.BUY));

        report.setRankingListIncome(this.getRankingByOperation(Operation.SELL));
        report.setRankingListOutcome(this.getRankingByOperation(Operation.BUY));

        LOG.info(" = = = Creating final report = = = ");
        // We show the final report with all the calculations and fixed dates
        System.out.println(report.toString());

    }

    public void setReport(Report report) {
        this.report = report;
    }

}
