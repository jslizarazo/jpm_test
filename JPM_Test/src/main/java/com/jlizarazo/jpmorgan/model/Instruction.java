package com.jlizarazo.jpmorgan.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class Instruction {

    private Entity entity;
    private Operation operation;
    private Currency currency;
    private LocalDate instructionDate,
            settlementDate;
    private BigDecimal agreedFx,
            price,
            units,
            totalTrade;

    public Instruction() {
    }

    public Instruction(Entity entity, Operation instruction, Currency currency, BigDecimal agreedFx, BigDecimal price, BigDecimal units, LocalDate instructionDate, LocalDate settlementDate) {
        this.entity = entity;
        this.operation = instruction;
        this.currency = currency;
        this.agreedFx = agreedFx.setScale(2, RoundingMode.CEILING);
        this.price = price.setScale(2, RoundingMode.CEILING);
        this.units = units.setScale(2, RoundingMode.CEILING);
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
    }

    public Entity getEntity() {
        return entity;
    }

    public Operation getOperation() {
        return operation;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public BigDecimal getTotalTrade() {
        return totalTrade;
    }

    public void setTotalTrade(BigDecimal totalTrade) {
        this.totalTrade = totalTrade;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    @Override
    public String toString() {
        return "|   " + entity + "   |    " + operation.getCode() + "     |   " + agreedFx + "   |   " + currency
                + "    |    " + instructionDate + "   |   " + settlementDate + "   |   " + units + "   |     " + price + "     |"
                + "\n -------------------------------------------------------------------------------------------------------------";
    }

}
