package com.example.consumingrest;

import com.example.consumingrest.model.ExchangeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);
	private static final String EXCHANGE_URL = "https://api.exchangeratesapi.io/latest";

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
		return args -> {
			ExchangeData exchangeData = restTemplate.getForObject(EXCHANGE_URL, ExchangeData.class);
			log.info("################################");
			log.info("- " + exchangeData.getBase());
			log.info("- " + exchangeData.getDate());
			log.info("- " + exchangeData.getRates().toString());
			log.info("################################");
		};
	}

}
