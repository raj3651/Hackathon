package com.hexaware.phoenix.socialtrust.dao;

import com.hexaware.phoenix.socialtrust.model.Applicant;
import com.hexaware.phoenix.socialtrust.model.TwitterActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwitterActivityRepository extends JpaRepository<TwitterActivity, Long> {
    public List<TwitterActivity> findAllByApplicant(Applicant applicant);
    public int countAllByApplicantAndFavorable(Applicant applicant, boolean favorable);
}
