package com.jlizarazo.jpmorgan.util;

import com.jlizarazo.jpmorgan.model.Currency;
import com.jlizarazo.jpmorgan.model.Entity;
import com.jlizarazo.jpmorgan.model.Instruction;
import com.jlizarazo.jpmorgan.model.Operation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public final class ReportUtils {

    private ReportUtils() {
    }

    public static List<Instruction> createSampleData() {
        List instructionList = new ArrayList();

        Instruction inst1 = new Instruction(Entity.BUT, Operation.BUY, Currency.SGP, new BigDecimal("0.50"), new BigDecimal("100.25"),
                new BigDecimal("200"), LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2));

        Instruction inst2 = new Instruction(Entity.BOC, Operation.SELL, Currency.AED, new BigDecimal("0.22"), new BigDecimal("150.50"),
                new BigDecimal("450"), LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 7));

        Instruction inst3 = new Instruction(Entity.WSM, Operation.BUY, Currency.EUR, new BigDecimal("1.10"), new BigDecimal("796.15"),
                new BigDecimal("300"), LocalDate.of(2018, Month.FEBRUARY, 9), LocalDate.of(2018, Month.FEBRUARY, 10));

        Instruction inst4 = new Instruction(Entity.CHB, Operation.BUY, Currency.AED, new BigDecimal("0.20"), new BigDecimal("300.25"),
                new BigDecimal("100"), LocalDate.of(2018, Month.MARCH, 14), LocalDate.of(2018, Month.MARCH, 16));

        Instruction inst5 = new Instruction(Entity.CHB, Operation.SELL, Currency.GBP, new BigDecimal("1.30"), new BigDecimal("976.85"),
                new BigDecimal("600"), LocalDate.of(2018, Month.APRIL, 3), LocalDate.of(2018, Month.APRIL, 5));

        Instruction inst6 = new Instruction(Entity.CMB, Operation.BUY, Currency.USD, new BigDecimal("1.00"), new BigDecimal("470.50"),
                new BigDecimal("530"), LocalDate.of(2018, Month.MAY, 17), LocalDate.of(2018, Month.MAY, 20));

        Instruction inst7 = new Instruction(Entity.BUT, Operation.SELL, Currency.SAR, new BigDecimal("0.30"), new BigDecimal("630.75"),
                new BigDecimal("865"), LocalDate.of(2018, Month.JUNE, 21), LocalDate.of(2018, Month.JUNE, 23));

        Instruction inst8 = new Instruction(Entity.WSM, Operation.SELL, Currency.SGP, new BigDecimal("0.50"), new BigDecimal("230.45"),
                new BigDecimal("760"), LocalDate.of(2018, Month.JULY, 4), LocalDate.of(2018, Month.JULY, 7));

        Instruction inst9 = new Instruction(Entity.CMB, Operation.SELL, Currency.EUR, new BigDecimal("1.10"), new BigDecimal("860.20"),
                new BigDecimal("150"), LocalDate.of(2018, Month.AUGUST, 24), LocalDate.of(2018, Month.AUGUST, 28));

        Instruction inst10 = new Instruction(Entity.BOC, Operation.BUY, Currency.USD, new BigDecimal("1.00"), new BigDecimal("666.66"),
                new BigDecimal("355"), LocalDate.of(2018, Month.SEPTEMBER, 13), LocalDate.of(2018, Month.SEPTEMBER, 15));

        instructionList.add(inst1);
        instructionList.add(inst2);
        instructionList.add(inst3);
        instructionList.add(inst4);
        instructionList.add(inst5);
        instructionList.add(inst6);
        instructionList.add(inst7);
        instructionList.add(inst8);
        instructionList.add(inst9);
        instructionList.add(inst10);

        return instructionList;
    }

    public static List<Instruction> createSampleDataTest() {
        List instructionList = new ArrayList();

        Instruction inst1 = new Instruction(Entity.BUT, Operation.BUY, Currency.SGP, new BigDecimal("0.50"), new BigDecimal("100"),
                new BigDecimal("200"), LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2));

        Instruction inst2 = new Instruction(Entity.BOC, Operation.SELL, Currency.AED, new BigDecimal("0.30"), new BigDecimal("150"),
                new BigDecimal("400"), LocalDate.of(2016, Month.JANUARY, 5), LocalDate.of(2016, Month.JANUARY, 8));

        Instruction inst3 = new Instruction(Entity.WSM, Operation.BUY, Currency.SAR, new BigDecimal("1.10"), new BigDecimal("200"),
                new BigDecimal("300"), LocalDate.of(2018, Month.FEBRUARY, 9), LocalDate.of(2018, Month.FEBRUARY, 10));

        Instruction inst4 = new Instruction(Entity.BUT, Operation.BUY, Currency.SGP, new BigDecimal("0.50"), new BigDecimal("100"),
                new BigDecimal("200"), LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2));

        Instruction inst5 = new Instruction(Entity.BUT, Operation.SELL, Currency.GBP, new BigDecimal("1.30"), new BigDecimal("100"),
                new BigDecimal("200"), LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 3));

        instructionList.add(inst1);
        instructionList.add(inst2);
        instructionList.add(inst3);
        instructionList.add(inst4);
        instructionList.add(inst5);

        return instructionList;
    }

}
