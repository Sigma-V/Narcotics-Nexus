package org.project.narcoticsnexus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class NarcoticsNexusApplication {

    public static void main(String[] args) {
        SpringApplication.run(NarcoticsNexusApplication.class, args);
    }

}
