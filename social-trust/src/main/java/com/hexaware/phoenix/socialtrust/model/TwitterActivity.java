package com.hexaware.phoenix.socialtrust.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TWITTER_ACTIVITY")
public class TwitterActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_ID")
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "APPLICANT_ID")
    private Applicant applicant;

    @Column(name = "ACTIVITY")
    private String activity;

    @Column(name = "ACTIVITY_DATE_TIME")
    private LocalDateTime activityDateTime;

    @Column(name = "FAVORABLE")
    private Boolean favorable;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDateTime getActivityDateTime() {
        return activityDateTime;
    }

    public void setActivityDateTime(LocalDateTime activityDateTime) {
        this.activityDateTime = activityDateTime;
    }

    public Boolean getFavorable() {
        return favorable;
    }

    public void setFavorable(Boolean favorable) {
        this.favorable = favorable;
    }
}
