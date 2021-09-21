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

import java.time.Instant;
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
        return service.techSupportAgent(uuid);
    }

    @GetMapping("/wait")
    public Flux<Issue> getWaitTime()
    {
        log.info("Geting waiting time");
        return service.waitTime();
    }

    @GetMapping("/resolveTime")
    public Flux<Issue> getResolveTime()
    {
        log.info("Geting resolve time");
        return service.resolveTime();
    }

    @GetMapping("/averageResolveTime")
    public Mono<Long> getAverageResolveTime() {
        log.info("Getting average resolve time");
        return service.getAverageResolveTime();
    }

}
