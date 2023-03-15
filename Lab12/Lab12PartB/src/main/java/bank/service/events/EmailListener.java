package bank.service.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailListener {
    @EventListener
    public void onEvent(EmailEvent event) {
        System.out.println("received event :" + event.getMessage());;
    }
}
