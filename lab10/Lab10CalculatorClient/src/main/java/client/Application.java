package client;

import generated.AddRequest;
import generated.MultiplyRequest;
import generated.SubtractRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CalculatorClient calculatorClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int num1 = 10;
		int num2 = 5;

		AddRequest addRequest = new AddRequest();
		addRequest.setNumber1(num1);
		addRequest.setNumber2(num2);

		SubtractRequest subtractRequest = new SubtractRequest();
		subtractRequest.setNumber1(num1);
		subtractRequest.setNumber2(num2);

		MultiplyRequest multiplyRequest = new MultiplyRequest();
		multiplyRequest.setNumber1(num1);
		multiplyRequest.setNumber2(num2);

		System.out.println(num1 + " + " + num2 +  " = " + calculatorClient.getSum(addRequest));
		System.out.println(num1 + " - " + num2 +  " = " + calculatorClient.getSub(subtractRequest));
		System.out.println(num1 + " * " + num2 +  " = " + calculatorClient.getMultiply(multiplyRequest));
	}

}


