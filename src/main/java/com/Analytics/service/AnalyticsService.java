package com.Analytics.service;
import com.Analytics.domain.Issue;
import com.Analytics.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Duration;
import java.time.Instant;
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



    public Mono<Long> getAverageResolveTime() {
        return repo.findAll().flatMap(x -> {

            Instant closedTime = x.getClosedTime();
            Instant reviewTime = x.getReviewTime();


            Duration timeDuration = Duration.between(reviewTime, closedTime);
            float duration = timeDuration.toMinutes();

            return Mono.just(duration);
        })
                .collectList()
                .flatMap(x -> {
                    if (x.size() > 0) {
                        long sum = 0;

                        for (int i = 0; i < x.size(); i++) {
                            sum += x.get(i);
                        }
                        long average = sum / x.size();
                        return Mono.just(average);
                    } else {
                        return Mono.just(0L);
                    }
                });

    }


}