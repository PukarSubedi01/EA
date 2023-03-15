package bank.service.events;

import bank.dao.ITraceRecord;
import bank.domain.TraceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TraceListener {
    Logger logger = LoggerFactory.getLogger(TraceListener.class);

    @Autowired
    ITraceRecord traceRecordRepository;
    @EventListener
    public void addTraceRecord(TraceEvent event){
        logger.info("Adding record to the database...");
        traceRecordRepository.save(event.getTraceRecord());
    }
}
