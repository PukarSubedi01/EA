package bank.dao;

import bank.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITraceRecord extends JpaRepository<TraceRecord, Long> {
}
