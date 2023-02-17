package io.github.toquery.example.spring.sharding.sphere.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan("io.github.toquery.example.spring.sharding.sphere")
@SpringBootApplication
public class ExampleSpringShardingSphereJPAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringShardingSphereJPAApplication.class, args);
    }

}
