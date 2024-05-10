package com.sha.springbootbookseller.service;
import com.sha.springbootbookseller.model.PurchaseHistory;
import com.sha.springbootbookseller.repository.IPurchaseHistoryRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@CrossOrigin(origins = "http://localhost:4200")
public class ReportService {

   @Autowired
    private IPurchaseHistoryRepository repository;

    //@RequestMapping(path = "/report", method = RequestMethod.GET)
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path= "C:\\YAZILIM";
        List<PurchaseHistory> purchases=repository.findAll();


        File file= ResourceUtils.getFile("C:\\YAZILIM\\spring-boot-book-seller\\src\\main\\resources\\templates\\purchase.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport("C:\\YAZILIM\\spring-boot-book-seller\\src\\main\\resources\\templates\\purchase.jrxml");

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(purchases);
        Map<String, Object> parameters=new HashMap<>();
        parameters.put("createdBy","Ozge Alkan");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters, dataSource);


        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\purchase.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\purchase.pdf");
        }

        return "report generated in path: " +path;
    }

}
