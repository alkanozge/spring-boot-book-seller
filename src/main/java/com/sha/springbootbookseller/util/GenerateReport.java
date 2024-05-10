package com.sha.springbootbookseller.util;

import com.sha.springbootbookseller.model.PurchaseHistory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;


@CrossOrigin(origins = "http://localhost:4200")
public class GenerateReport {

   /* public  GenerateReport(DataSource dataSource){
        this.dataSource = dataSource;

    }*/

    //private static  DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(GenerateReport.class);

    public static byte[] generateReport() {
        try {

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_book", "admin", "1234");

           // File reportTemplateFile = ResourceUtils.getFile("C:\\YAZILIM\\spring-boot-book-seller\\src\\main\\resources\\templates\\purchase.jrxml");

           //JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplateFile.getAbsolutePath());
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\YAZILIM\\spring-boot-book-seller\\src\\main\\resources\\templates\\purchase.jrxml"); //Compile the report from .jrxml to .jasper

            Map<String, Object> parameters = new HashMap<>();

            List<Map<String, Object>> reportData = new ArrayList<>(); //fetch

            //String sql = "SELECT b.id AS book_id, b.title AS book_title, COUNT(*) AS number_of_sales FROM sc_book.purchase_history ph JOIN sc_book.books b ON ph.book_id = b.id GROUP BY b.id, b.title";

            String sql= "SELECT b.id AS book_id, b.title AS book_title, COUNT(*) AS number_of_sales\n" +
                    "                      FROM sc_book.purchase_history ph\n" +
                    "                               JOIN sc_book.books b ON ph.book_id = b.id\n" +
                    "                      GROUP BY b.id, b.title";
            //debug here

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("bookId", resultSet.getLong("book_id"));
                    row.put("bookTitle", resultSet.getString("book_title"));
                    row.put("numberOfSales", resultSet.getLong("number_of_sales"));
                    reportData.add(row);

                    logger.debug("Fetched Data - bookId: {}, bookTitle: {}, numberOfSales: {}",
                            resultSet.getLong("book_id"),
                            resultSet.getString("book_title"),
                            resultSet.getLong("number_of_sales"));
                }
            } catch (SQLException e) {
               // e.printStackTrace();
                logger.error("Error executing SQL query: {}", e.getMessage());
                e.printStackTrace();
            }

            //CompletableFuture<Connection> dataSourceCompletableFuture = new CompletableFuture<>();


            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData); //convert the data list into a bean collection
           // dataSourceCompletableFuture.complete(dataSource.getData().);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource); //fill with data
            byte[] reportBytes = JasperExportManager.exportReportToPdf(jasperPrint); //export
            logger.debug("Generated PDF report with {} bytes of data.", reportBytes.length);
            logger.debug("Executing SQL query: {}", sql);
            logger.debug("Retrieved data: {}", reportData);


            logger.info("Finished report generation.");


            return reportBytes;

        } catch (Exception e) {
            logger.error("An error occurred during report generation.", e);
            return null;
        }

    }

    public static void main(String[] args) {
        byte[] reportBytes = generateReport();
        System.out.println("Report generated successfully.");
    }
}

