package test.java.ro.agilehub.javacourse.car.hire.fleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "ro.agilehub.javacourse.car.hire")
@SpringBootApplication
public class TestConfiguration {

    public static void main(final String[] args) {
        SpringApplication.run(TestConfiguration.class, args);
    }
}
