package com.Analytics.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;

import java.time.Instant;

@Data
public class CloseAndReviewTime {

    @Column
    Instant closedTime;
    @Column
    Instant reviewTime;

}
