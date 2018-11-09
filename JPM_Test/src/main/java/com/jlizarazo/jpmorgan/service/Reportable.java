package com.jlizarazo.jpmorgan.service;

import com.jlizarazo.jpmorgan.model.DailyAmount;
import com.jlizarazo.jpmorgan.model.Operation;
import com.jlizarazo.jpmorgan.model.Ranking;
import com.jlizarazo.jpmorgan.model.Instruction;
import java.math.BigDecimal;
import java.util.List;

public interface Reportable {

    List<DailyAmount> calculateByOperation(Operation operation);

    List<Ranking> getRankingByOperation(Operation operation);

    void checkSettlementDate();

    void createReport(List<Instruction> instructionList);

    default BigDecimal calculateTradeAmount(Instruction instruction) {
        BigDecimal agreedFx = instruction.getAgreedFx();
        BigDecimal price = instruction.getPrice();
        BigDecimal units = instruction.getUnits();

        BigDecimal amount = agreedFx.multiply(price.multiply(units)).setScale(3);
        return amount;
    }

}
