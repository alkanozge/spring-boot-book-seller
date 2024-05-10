package com.sha.springbootbookseller.controller;

import com.sha.springbootbookseller.util.GenerateReport;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    @GetMapping("/api/generate-report")
    public ResponseEntity<byte[]> generateReport() throws IOException {
        try {
            byte[] reportBytes = GenerateReport.generateReport();
           // System.out.println("Report Bytes Size: " + reportBytes.length);

           // Path reportPath = Files.write(Paths.get("C:\\YAZILIM\\spring-boot-book-seller\\src\\main\\resources\\purchase.pdf"), reportBytes, StandardOpenOption.CREATE);

            //File reportFile = File.createTempFile("purchase", ".pdf");
            //Path reportPath = reportFile.toPath();
            //Files.write(reportPath, reportBytes);

            // reportBytes = Files.readAllBytes(reportPath); //I store the pdf file in reportBytes

            //ByteArrayResource resource = new ByteArrayResource(reportBytes);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "aaa.pdf");


            ByteArrayResource resource = new ByteArrayResource(reportBytes);

            //Returning the report as a downloadable file
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(reportBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}



//package com.sha.springbootbookseller.controller;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//public class ReportController {
//
//    @GetMapping("/report")
//    public ModelAndView showReport() {
//        ModelAndView modelAndView = new ModelAndView("report");
//        // You can add any necessary data to the model here
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/download/report.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//    @ResponseBody
//    public byte[] downloadReport() throws IOException {
//        // Load the PDF file from the classpath
//        ClassPathResource pdfFile = new ClassPathResource("staticfiles/report.pdf");
//        // Read the file content into a byte array
//        return Files.readAllBytes(pdfFile.getFile().toPath());
//    }
//}
