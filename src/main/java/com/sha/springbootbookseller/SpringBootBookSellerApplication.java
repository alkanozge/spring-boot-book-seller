package com.sha.springbootbookseller;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class SpringBootBookSellerApplication {
	//@Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


	//***********
//	@Autowired
//	private ReportService service;

//	@GetMapping("/report/{format}")
//	public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
//		return service.exportReport(format);
//	}

	//**********

	public static void main(String[] args) throws JRException {

		SpringApplication.run(SpringBootBookSellerApplication.class, args);




	}
}