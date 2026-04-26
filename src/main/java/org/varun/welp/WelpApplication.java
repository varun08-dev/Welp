package org.varun.welp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WelpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WelpApplication.class, args);
    }

}
