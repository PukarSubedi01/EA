package bank;

import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableKafka
@EnableJms
@EnableScheduling
public class Application {

	@Autowired
	private IAccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
