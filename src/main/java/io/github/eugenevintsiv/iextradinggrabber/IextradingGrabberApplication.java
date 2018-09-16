package io.github.eugenevintsiv.iextradinggrabber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAutoConfiguration
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "io.github.eugenevintsiv.**")
public class IextradingGrabberApplication {

	public static void main(String[] args) {
		SpringApplication.run(IextradingGrabberApplication.class, args);
	}
}
