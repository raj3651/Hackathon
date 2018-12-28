package com.hexaware.phoenix.socialtrust.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "APPLICANT")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICANT_ID")
    private Long applicantId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private String age;

    @Column(name = "TWITTER_HANDLE")
    private String twitterHandle;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TwitterActivity> twitterActivities;

    @Column(name = "APPLICANT_TRUST_SCORE")
    private Long applicantTrustScore;

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public Set<TwitterActivity> getTwitterActivities() {
        return twitterActivities;
    }

    public void setTwitterActivities(Set<TwitterActivity> twitterActivities) {
        this.twitterActivities = twitterActivities;
    }

    public Long getApplicantTrustScore() {
        return applicantTrustScore;
    }

    public void setApplicantTrustScore(Long applicantTrustScore) {
        this.applicantTrustScore = applicantTrustScore;
    }
}
