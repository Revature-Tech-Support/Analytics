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
    public Flux<Issue> getIssuesCompletedByTechSupport(UUID uuid){return repo.getResolvedIssuesByTechSupport(uuid);}

    /**
     * Returns the average wait time per all issues
     */
    public Mono<Long> getWaitTime() {
        Flux<Long> flux = repo.getTimestamps()
                .flatMap(issue -> Mono.just(Duration.between(issue.getOpenTime(), issue.getReviewTime()).toMinutes()));
        return CalculateAverage(flux);
    }

    /**
     * Return the average wait time per issue
     */
    public Mono<Long> getWaitTimeByIssue(String issueTitle) {
        Flux<Long> flux = repo.getTimestampsFromIssue(issueTitle)
                .flatMap(issue -> Mono.just(Duration.between(issue.getOpenTime(), issue.getReviewTime()).toMinutes()));
        return CalculateAverage(flux);
    }

    /**
     * Returns the average resolve time per all issues
     */
    public Mono<Long> getResolveTime() {
        Flux<Long> flux = repo.getTimestamps()
                .flatMap(issue -> Mono.just(Duration.between(issue.getReviewTime(), issue.getClosedTime()).toMinutes()));
        return CalculateAverage(flux);

    }

    /**
     * Returns the average resolve time per issue
     */
    public Mono<Long> getResolveTimeByIssue(String issueTitle) {
        Flux<Long> flux = repo.getTimestampsFromIssue(issueTitle)
                .flatMap(issue -> Mono.just(Duration.between(issue.getReviewTime(), issue.getClosedTime()).toMinutes()));
        return CalculateAverage(flux);
    }

    /**
     * Calculates the average number from a Flux
     * @param flux Flux to average
     * @return Average number
     */
    private Mono<Long> CalculateAverage(Flux<Long> flux) {
        return flux
                //Converts flux to an iterable list type within Mono<List<Long>>
                .collectList()
                //Map it to single value
                .flatMap(x -> {
                    long sum = 0;

                    for (int i =0;i < x.size(); i++) {
                        sum += x.get(i);
                    }

                    return Mono.just(sum / x.size());
                });
    }

}