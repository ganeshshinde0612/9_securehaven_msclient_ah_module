package com.securehaven._securehaven_msclient_ah_module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.securehaven._securehaven_msclient_ah_module.model.Loan_Disbursement;

@Repository
public interface LoanDisbursementRepository extends JpaRepository<Loan_Disbursement, Integer> {

	@Query(value = "from Loan_Disbursement where app_id = ?1")
	public Loan_Disbursement findByAppid(int app_id);
}
