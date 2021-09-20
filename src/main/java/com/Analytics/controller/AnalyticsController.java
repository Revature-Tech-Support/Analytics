package com.Analytics.controller;

import com.Analytics.domain.Issue;
import com.Analytics.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/analysis")
public class AnalyticsController {

    private AnalyticsService service;

    public AnalyticsController(AnalyticsService service)
    {
        this.service = service;
    }
    public AnalyticsController()
    {

    }

    @GetMapping("/resolved")
    public Flux<Issue> getResolvedIssues()
    {
        return service.closedIssues();
    }
}
