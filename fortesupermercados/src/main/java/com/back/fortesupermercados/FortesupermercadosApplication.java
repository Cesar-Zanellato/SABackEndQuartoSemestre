package com.back.fortesupermercados;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.back.fortesupermercados.data.DataLoader;

@SpringBootApplication
public class FortesupermercadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortesupermercadosApplication.class, args);
    }

    public ApplicationRunner dataLoaderRunner(DataLoader dataLoader) {
        return args -> {
            dataLoader.run(args);
        };
    }
}
