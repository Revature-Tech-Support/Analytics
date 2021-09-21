package com.Analytics.service;
import com.Analytics.domain.Issue;
import com.Analytics.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Duration;
import java.util.UUID;

@Service
public class AnalyticsService {

    @Autowired
    private final AnalyticsRepository repo;

    public AnalyticsService(AnalyticsRepository repo)
    {
        this.repo =repo;
    }

    /**
     * Gets all issues that are resolved
     * @return
     */
    public Flux<Issue> closedIssues(){
        return  repo.getResolvedIssues();
    }

    /**
     * Gets all issues that were completed by a specified tech support agent
     * @param uuid id of tech support agent
     * @return Issues closed by tech support agent
     */
    public Flux<Issue> getIssuesCompletedByTechSupport(UUID uuid){return repo.getIssuesCompletedByTechSupport(uuid);}

    /**
     * Calculate the average wait time
     * @return Average wait time in between issues in minutes
     */
    public Mono<Long> getWaitTime() {
        return repo.getWaitTime()
                //Returns a flux contain difference between the closed time and review time in minutes
                .flatMap(issue -> {
                    Duration timeDuration = Duration.between(issue.getOpenTime(), issue.getReviewTime());
                    //convert to readable format (minutes)
                    Long duration = timeDuration.toMinutes();
                    return Mono.just(duration);
                })
                //Convert flux into iterable list
                .collectList()
                //Calculate the average wait time
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

    /**
     * Calculates the average time to resolve an issue and returns it in Minutes
     * @return Average time to resolve an issue in minutes
     */
    public Mono<Long> getResolveTime() {
        return repo.getResolveTime()
                //Returns the difference between the closed time and review time
                .flatMap(x -> {
                    //Calculate the difference between closed time and review time
                    Duration timeDuration = Duration.between(x.getReviewTime(), x.getClosedTime());
                    //convert to readable format (minutes)
                    Long duration = timeDuration.toMinutes();

                    return Mono.just(duration);
                })
                //Convert the flux to an iterable list
                .collectList()
                //Calculate the average resolve and return a Mono<Long>
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