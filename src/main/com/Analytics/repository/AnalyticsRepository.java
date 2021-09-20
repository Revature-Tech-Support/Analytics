package com.Analytics.repository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AnalyticsRepository{
    @Query ("SELECT COUNT(*) FROM Issue WHERE inProgress = False ALLOW FILTERING;")
    Flux<Issue> getResolvedIssues();

}