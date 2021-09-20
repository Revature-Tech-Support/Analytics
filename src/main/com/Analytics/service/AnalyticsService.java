package com.Analytics.service;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AnalyticsService {

    private final AnalyticsRepository repo;
    public Flux<Issue> closedIssues(){
        repo.getResolvedIssues();
    }
}