package com.jlizarazo.jpmorgan;

import com.jlizarazo.jpmorgan.service.ReportService;
import com.jlizarazo.jpmorgan.util.ReportUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jlizarazo.jpmorgan.service.Reportable;

@SpringBootApplication
public class ReportingEngineApp {

    public static void main(String[] args) {
        SpringApplication.run(ReportingEngineApp.class, args);

        Reportable rs = new ReportService();
        rs.createReport(ReportUtils.createSampleData());
    }
}
