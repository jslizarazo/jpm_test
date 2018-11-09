package com.jlizarazo.jpmorgan.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class DailyAmount {

    private LocalDate day;
    private BigDecimal amount;

    public DailyAmount() {
    }

    public DailyAmount(LocalDate day, BigDecimal amount) {
        this.day = day;
        this.amount = amount;
    }

    public LocalDate getDay() {
        return day;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Date: " + day + "\nAmount: " + amount
                + "\n ------------------------------";
    }

}
