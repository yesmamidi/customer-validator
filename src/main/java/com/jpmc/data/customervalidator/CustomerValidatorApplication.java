package com.jpmc.data.customervalidator;

import com.jpmc.data.customervalidator.utility.DataProviderConfigs;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
@ConfigurationPropertiesScan("com.jpmc.data.customervalidator")
public class CustomerValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerValidatorApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(DataProviderConfigs dataProviderConfigs) {
        return c -> {
            System.out.println("Printing props" + dataProviderConfigs);
        };
    }

}
