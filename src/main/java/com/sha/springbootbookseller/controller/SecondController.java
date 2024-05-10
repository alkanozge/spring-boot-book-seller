package com.sha.springbootbookseller.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

@RestController
public class SecondController {
    @GetMapping("/pdf")
    public String generatePdf() throws FileNotFoundException, JRException {
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(null);
        JasperReport compileReport= JasperCompileManager.compileReport(new FileInputStream("scr/main/resources/second.jrxml"));

        HashMap<String, Object > map= new HashMap<>();
        JasperPrint report =JasperFillManager.fillReport(compileReport,map, beanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(report, "second.pdf");

        return "generated";
    }
}
