package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatorMessageListener {
 private int previousValue = 0;
    @JmsListener(destination = "calculatorQueue")
    public void receiveMessage(final String calculatorAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Calculator calculator
                    = objectMapper.readValue(calculatorAsString, Calculator.class);
            previousValue = calculator.calculate(previousValue);
            System.out.println("JMS receiver received message:" + previousValue);

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("JMS receiver: Cannot convert : " + calculatorAsString+" to a Calculator object");
        }
     }


}

