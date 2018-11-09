package com.jlizarazo.jpmorgan.model;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class Ranking {

    private Entity entity;
    private BigDecimal totalAmount;

    public Ranking() {
    }

    public Ranking(Entity entity, BigDecimal totalIncome) {
        this.entity = entity;
        this.totalAmount = totalIncome;
    }

    public Entity getEntity() {
        return entity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Entity: " + entity.getName() + "\nAmount: " + totalAmount
                + "\n ------------------------------";
    }

}
