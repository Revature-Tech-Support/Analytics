package com.Analytics.controller;

import com.Analytics.domain.Issue;
import com.Analytics.service.AnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/analysis")
public class AnalyticsController {

    private final AnalyticsService service;

    private static final Logger log = LoggerFactory.getLogger("controller");

    public AnalyticsController(AnalyticsService service)
    {
        log.info("Creating new Analytics Controller");
        this.service = service;
    }

    @GetMapping("/resolved")
    public Flux<Issue> getResolvedIssues()
    {
        log.info("Getting resolved Issues");
        return service.closedIssues();
    }

    @GetMapping("/resolved/{uuid}")
    public Flux<Issue> getTechResolvedIssues(@PathVariable UUID uuid)
    {
        log.info("Getting Resolved Issues by Tech");
        return service.getIssuesCompletedByTechSupport(uuid);
    }

    @GetMapping("/wait")
    public Mono<Long> getWaitTime()
    {
        log.info("Getting waiting time");
        return service.getWaitTime();
    }

    @GetMapping("/wait/{title}")
    public Mono<Long> getWaitTimeByIssue(@PathVariable String title) {
        log.info("Getting wait time per issue: " + title);
        return service.getWaitTimeByIssue(title);
    }

    @GetMapping("/resolveTime")
    public Mono<Long> getResolveTime()
    {
        log.info("Getitng resolve time");
        return service.getResolveTime();
    }

    @GetMapping("/resolveTime/{title}")
    public Mono<Long> getResolveTimeByIssue(@PathVariable String title)
    {
        log.info("Getting resolve time per issue: " + title);
        return service.getResolveTimeByIssue(title);
    }

}
