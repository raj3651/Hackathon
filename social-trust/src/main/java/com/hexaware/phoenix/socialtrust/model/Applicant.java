package com.hexaware.phoenix.socialtrust.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "APPLICANT")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICANT_ID")
    private @Getter @Setter Long applicantId;

    @Column(name = "FIRST_NAME")
    private @Getter @Setter String firstName;

    @Column(name = "LAST_NAME")
    private @Getter @Setter String lastName;

    @Column(name = "AGE")
    private @Getter @Setter String age;

    @Column(name = "NUM_OF_DEVICES")
    private @Getter @Setter Long numOfDevices;

    @Column(name = "NUM_OF_LOCKOUTS")
    private @Getter @Setter Long numOfLockouts;

    @Column(name = "NUM_OF_FAIL_LOGINS")
    private @Getter @Setter Long numOfFailLogins;

    @Column(name = "NUM_OF_MFA_AUTHS")
    private @Getter @Setter Long numOfMFAAuths;

    @Column(name = "ACCT_AGE")
    private @Getter @Setter Double acctAge;

    @Column(name = "TWITTER_HANDLE")
    private @Getter @Setter String twitterHandle;

    @Column(name = "APPLICANT_TRUST_SCORE")
    private @Getter @Setter Double applicantTrustScore;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TwitterActivity> twitterActivities;

    public Set<TwitterActivity> getTwitterActivities() {
        return twitterActivities;
    }

    public void setTwitterActivities(Set<TwitterActivity> twitterActivities) {
        this.twitterActivities = twitterActivities;
    }
  }
