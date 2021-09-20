package com.Analytics.controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping(value = "/analysis")
public class AnalysisController {

    private final AnalyticsService service;
    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }


    @GetMapping("/resolved")
    public Flux<Stocks> getResolvedIssues() {
        //log.info("Showing all stocks");
        return service.closedIssues();
    }
