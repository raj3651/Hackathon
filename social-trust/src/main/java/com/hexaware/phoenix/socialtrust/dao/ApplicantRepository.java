package com.hexaware.phoenix.socialtrust.dao;

import com.hexaware.phoenix.socialtrust.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
