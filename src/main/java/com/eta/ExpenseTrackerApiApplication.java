package com.eta;

import com.eta.model.Category;
import com.eta.model.Expense;
import com.eta.model.PaymentMode;
import com.eta.model.User;
import com.eta.repository.ExpenseRepository;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExpenseTrackerApiApplication {//implements CommandLineRunner{

	/*@Autowired
	private ExpenseRepository expenseRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {

		List<Expense> expenses = new ArrayList<>();

		Expense e1 = new Expense();
		e1.setDescription("Expense 1");
		e1.setAmount(200.0);
		e1.setDate(LocalDate.now());
		e1.setPaymentMode(PaymentMode.CREDITCARD);
		e1.setCategory(Category.FOOD);

		Expense e2 = new Expense(400.0,Category.UTILITIES,LocalDate.now(),"Expense 2",PaymentMode.DEBITCARD,1L);

		expenseRepository.saveAll(expenses);
	}

	@Bean
	public OpenAPI springEmsOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Expense tracker API")
						.description("ETA h2 application")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
						.description("EMS Wiki Documentation")
						.url("https://ems.wiki.github.org/docs"));
	}*/
}
