package com.back.fortesupermercados;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.back.fortesupermercados.data.DataLoader;

@SpringBootApplication
public class FortesupermercadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortesupermercadosApplication.class, args);
	}

	@Bean
    public ApplicationRunner dataLoaderRunner(DataLoader dataLoader) {
        return args -> {
            dataLoader.run(args);
        };
    }
}
