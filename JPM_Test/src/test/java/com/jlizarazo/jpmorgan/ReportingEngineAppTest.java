package com.jlizarazo.jpmorgan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReportingEngineAppTest {

    @Test
    public void contextLoads() {
    }

    /**
     * Test of main method, of class ReportingEngineApp. We just check that it doesn't throw any error or exception
     */
    @Test
    public void testMain() {
        String[] args = {"Args"};
        ReportingEngineApp.main(args);
    }

}
