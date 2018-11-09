package com.jlizarazo.jpmorgan.util;

import com.jlizarazo.jpmorgan.model.Currency;
import com.jlizarazo.jpmorgan.model.Entity;
import com.jlizarazo.jpmorgan.model.Instruction;
import com.jlizarazo.jpmorgan.model.Operation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ReportUtilsTest {

    Instruction inst1;

    @Before
    public void setUp() {
        inst1 = new Instruction(Entity.BUT, Operation.BUY, Currency.SGP, new BigDecimal(0.50), new BigDecimal(100.25),
                new BigDecimal(200), LocalDate.of(2016, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 2));
    }

    @Test
    public void testCreateSampleData() {
        List<Instruction> result = ReportUtils.createSampleData();

        assertEquals(10, result.size());
        assertTrue(inst1.getInstructionDate().equals(result.get(0).getInstructionDate()));
        assertFalse(inst1.getInstructionDate().equals(result.get(9).getInstructionDate()));
    }

}
