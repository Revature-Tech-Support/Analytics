package com.Analytics.service;
import com.Analytics.domain.Issue;
import com.Analytics.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AnalyticsService {

    @Autowired
    private final AnalyticsRepository repo;

    public AnalyticsService(AnalyticsRepository repo)
    {
        this.repo =repo;
    }

    public Flux<Issue> closedIssues(){
        return  repo.getResolvedIssues();
    }

    public Flux<Issue> techSupportAgent(UUID uuid){return repo.getTechSupport(uuid);}

    public Flux<Issue> waitTime(){return repo.getWaitTime();}

    public Flux<Issue> resolveTime(){return repo.getResolveTime();}


}