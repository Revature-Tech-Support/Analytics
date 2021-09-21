package com.Analytics.repository;
import com.Analytics.domain.Issue;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.annotation.Nonnull;
import java.util.UUID;

@Repository
public interface AnalyticsRepository extends ReactiveCassandraRepository<Issue, UUID> {
    @Query("SELECT * FROM Issue WHERE inQueue = False ALLOW FILTERING;")
    public Flux<Issue> getResolvedIssues();

    @Query("SELECT * FROM Issue WHERE closedBy = ?0 ALLOW FILTERING;")
    public Flux<Issue> getIssuesCompletedByTechSupport(UUID tech);

    @Query("SELECT closedTime, reviewTime from issue")
    public Flux<Issue> getResolveTime();

    @Query("SELECT reviewTime, openTime FROM issue")
    public Flux<Issue> getWaitTime();

}