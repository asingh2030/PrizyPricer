package com.prizy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.prizy.price.service.IdealPriceService;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.prizy.*")
public class AppConfig {
	
	@Value("${prizy.ideal.price.service}")
	private String priceServiceType;
	@Autowired
	ApplicationContext context;
	
	@Bean(name="idealPriceService")
	public IdealPriceService getIdealPriceService(){
		assert priceServiceType != null : "Ideal price service type must not null. "+priceServiceType;
		String beanName = new StringBuilder(priceServiceType.toLowerCase()).append("IdealPriceService").toString();
		IdealPriceService bean = (IdealPriceService) context.getBean(beanName);
		return bean;
	}
}
