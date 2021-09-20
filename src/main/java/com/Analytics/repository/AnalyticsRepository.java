package com.Analytics.repository;
import com.Analytics.domain.Issue;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface AnalyticsRepository extends ReactiveCassandraRepository<Issue, UUID> {
    @Query("SELECT COUNT(*) FROM Issue WHERE inProgress = False ALLOW FILTERING;")
    public Flux<Issue> getResolvedIssues();

    @Query("SELECT closedBy, COUNT(*) FROM Issue WHERE inProgress = False GROUP BY closedBy ALLOW FILTERING;")
    public Flux<Issue> getTechSupport();

}