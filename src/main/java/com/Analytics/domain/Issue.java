package com.Analytics.domain;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.annotation.Id;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.UUID;

@Data
@Table("Issue")
public class Issue{
    @Id
    @PrimaryKey
    private UUID issueID;
    private String issueTitle;
    private UUID openedBy;
    private UUID closedBy;
    private Instant openTime;
    private Instant reviewTime;
    private Instant closedTime;
    private boolean inQueue;

    public Issue(UUID issueID, String issueTitle, UUID openedBy, UUID closedBy, Instant openTime, Instant reviewTime, Instant closedTime, boolean inQueue) {
        this.issueID = issueID;
        this.issueTitle = issueTitle;
        this.openedBy = openedBy;
        this.closedBy = closedBy;
        this.openTime = openTime;
        this.reviewTime = reviewTime;
        this.closedTime = closedTime;
        this.inQueue = inQueue;
    }

    @Nonnull
    public static Issue from(Issue issue)
    {
        return new Issue(issue.issueID,issue.issueTitle,issue.openedBy,issue.closedBy, issue.openTime, issue.reviewTime, issue.closedTime, issue.inQueue);
    }
}