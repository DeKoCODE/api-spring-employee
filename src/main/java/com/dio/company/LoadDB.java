package com.dio.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {

    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner applicationRunner(EmployeeRepository employeerepository){
        return args -> {
            log.info("Log of event save user 1:" + employeerepository.save(new Employee("Denison", "Assistente","Rua Joao Silveira, 1821")));
            log.info("Log of event save user 2:" + employeerepository.save(new Employee("Rodolfo", "Assistente","Rua Ernesto Cunha, 1821")));
        };
    }
}
