package bank.service.events;

import bank.domain.TraceRecord;

public class TraceEvent {

    private TraceRecord traceRecord;

    public TraceEvent(TraceRecord traceRecord){
        this.traceRecord = traceRecord;
    }

    public TraceRecord getTraceRecord() {
        return traceRecord;
    }
}
