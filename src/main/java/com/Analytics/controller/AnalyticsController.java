package com.Analytics.controller;

import com.Analytics.domain.Issue;
import com.Analytics.service.AnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
}
