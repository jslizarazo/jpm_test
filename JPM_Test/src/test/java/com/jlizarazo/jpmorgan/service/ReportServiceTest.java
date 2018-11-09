package com.jlizarazo.jpmorgan.service;

import com.jlizarazo.jpmorgan.model.DailyAmount;
import com.jlizarazo.jpmorgan.model.Entity;
import com.jlizarazo.jpmorgan.model.Instruction;
import com.jlizarazo.jpmorgan.model.Operation;
import com.jlizarazo.jpmorgan.model.Ranking;
import com.jlizarazo.jpmorgan.model.Report;
import com.jlizarazo.jpmorgan.util.ReportUtils;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    private List<Instruction> instructionList;

    @Spy
    private ReportService service;

    private Report report;

    @Before
    public void setUp() {
        instructionList = ReportUtils.createSampleDataTest();
        report = new Report(instructionList);
        service.setReport(report);
    }

    /**
     * Test of calculateByOperation method, of class ReportService.
     */
    @Test
    public void testCalculateByOperationBuy() {
        Operation operation = Operation.BUY;
        List<DailyAmount> result = service.calculateByOperation(operation);
        assertEquals(2, result.size());
        assertEquals(20000, result.get(0).getAmount().intValue());
        assertEquals("2016-01-02", result.get(0).getDay().toString());
    }

    @Test
    public void testCalculateByOperationSell() {
        Operation operation = Operation.SELL;
        List<DailyAmount> result = service.calculateByOperation(operation);
        assertEquals(2, result.size());
        assertEquals(26000, result.get(0).getAmount().intValue());
        assertEquals("2016-01-03", result.get(0).getDay().toString());
    }

    /**
     * Test of getRankingByOperation method, of class ReportService.
     */
    @Test
    public void testGetRankingByOperationBuy() {
        Operation operation = Operation.BUY;
        List<Ranking> result = service.getRankingByOperation(operation);
        assertEquals(5, result.size());
        assertEquals(66000, result.get(0).getTotalAmount().intValue());
        assertEquals(Entity.WSM, result.get(0).getEntity());
    }

    @Test
    public void testGetRankingByOperationSell() {
        Operation operation = Operation.SELL;
        List<Ranking> result = service.getRankingByOperation(operation);
        assertEquals(5, result.size());
        assertEquals(26000, result.get(0).getTotalAmount().intValue());
        assertEquals(Entity.BUT, result.get(0).getEntity());
    }

    /**
     * Test of checkSettlementDate method, of class ReportService.
     */
    @Test
    public void testCheckSettlementDate() {
        assertEquals("2016-01-02", instructionList.get(0).getSettlementDate().toString());
        service.checkSettlementDate();
        assertEquals("2016-01-04", instructionList.get(0).getSettlementDate().toString());
        assertEquals("2018-02-11", instructionList.get(2).getSettlementDate().toString());
    }

    /**
     * Test of createReport method, of class ReportService.
     */
    @Test
    public void testCreateReport() {
        service.createReport(instructionList);
        verify(service, atLeastOnce()).checkSettlementDate();
    }

}
