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


    public UUID getIssueID() {
        return issueID;
    }

    public void setIssueID(UUID issueID) {
        this.issueID = issueID;
    }

    public UUID getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(UUID openedBy) {
        this.openedBy = openedBy;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public UUID getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(UUID closedBy) {
        this.closedBy = closedBy;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}