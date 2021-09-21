package com.Analytics.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.annotation.Id;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("Issue")
public class Issue{
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID issueID;
    @Column
    private String issueTitle;
    @Column
    private UUID openedBy;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private UUID closedBy;
    @Column
    private Instant openTime;
    @Column
    private Instant reviewTime;
    @Column
    private Instant closedTime;
    @Column
    private boolean inQueue;

    public Issue(Instant closedTime, Instant reviewTime) {
        this.closedTime = closedTime;
        this.reviewTime = reviewTime;
    }

    @Nonnull
    public static Issue from(Issue issue)
    {
        return new Issue(issue.issueID,issue.issueTitle,issue.openedBy,issue.closedBy, issue.openTime, issue.reviewTime, issue.closedTime, issue.inQueue);
    }
}