package com.Analytics.repository;
import com.Analytics.domain.CloseAndReviewTime;
import com.Analytics.domain.Issue;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.annotation.Nonnull;
import java.util.UUID;

@Repository
public interface AnalyticsRepository extends ReactiveCassandraRepository<Issue, UUID> {
    @Nonnull
    @Query("SELECT * FROM Issue WHERE inQueue = False ALLOW FILTERING;")
    public Flux<Issue> getResolvedIssues();

    @Nonnull
    @Query("SELECT * FROM Issue WHERE closedBy = ?0 ALLOW FILTERING;")
    public Flux<Issue> getTechSupport(UUID tech);

    @Nonnull
    @Query("SELECT AVG(DAYDIFF(closedTime,reviewTime)) From Issue GROUP BY closedBy")
    public Flux<Issue> getResolveTime();

    @Nonnull
    @Query("SELECT AVG(TIMEDIFF(reviewTime,openTime)) From Issue GROUP BY closedBy")
    public Flux<Issue> getWaitTime();

}