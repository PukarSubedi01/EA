package bank.service.events;

import bank.dao.ITraceRecord;
import bank.domain.TraceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TraceListener {
    @Autowired
    ITraceRecord traceRecordRepository;
    @EventListener
    public void addTraceRecord(TraceEvent event){
        System.out.println("Adding record to the database...");
        traceRecordRepository.save(event.getTraceRecord());
    }
}
