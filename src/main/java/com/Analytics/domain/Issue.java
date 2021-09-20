package com.Analytics.domain;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Table("Issue")
public class Issue{
    @Id
    @PrimaryKey
    private UUID issueID;
    private String issueTitle;
    private UUID openedBy;
    private UUID closedBy;
    private String openTime;
    private String reviewTime;
    private String closedTime;
    private boolean inProgress;

    public Issue(UUID issueID, String issueTitle, UUID openedBy, UUID closedBy, String openTime, String reviewTime, String closedTime, boolean inProgress) {
        this.issueID = issueID;
        this.issueTitle = issueTitle;
        this.openedBy = openedBy;
        this.closedBy = closedBy;
        this.openTime = openTime;
        this.reviewTime = reviewTime;
        this.closedTime = closedTime;
        this.inProgress = inProgress;
    }

    public Issue() {

    }
}