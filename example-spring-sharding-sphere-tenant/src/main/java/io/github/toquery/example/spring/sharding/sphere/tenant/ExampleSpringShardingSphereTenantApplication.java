package io.github.toquery.example.spring.sharding.sphere.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("io.github.toquery.example.spring.sharding.sphere")
@EnableJpaRepositories("io.github.toquery.example.spring.sharding.sphere.jpa.modules")
@SpringBootApplication(scanBasePackages = "io.github.toquery.example.spring.sharding.sphere.jpa.bff")
public class ExampleSpringShardingSphereTenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringShardingSphereTenantApplication.class, args);
	}

}
