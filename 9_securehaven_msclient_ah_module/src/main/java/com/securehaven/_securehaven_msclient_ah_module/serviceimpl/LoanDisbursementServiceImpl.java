package com.securehaven._securehaven_msclient_ah_module.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securehaven._securehaven_msclient_ah_module.model.Loan_Disbursement;
import com.securehaven._securehaven_msclient_ah_module.repository.LoanDisbursementRepository;
import com.securehaven._securehaven_msclient_ah_module.servicei.LoanDisebursementServicei;

@Service
public class LoanDisbursementServiceImpl implements LoanDisebursementServicei{

	@Autowired
	LoanDisbursementRepository ldr;
	
	@Override
	public void saveDisbursementDetails(Loan_Disbursement ld) {
		
		ldr.save(ld);
	}

	@Override
	public Loan_Disbursement getdisbursedetails(int app_id) {
		
		
		return ldr.findByAppid(app_id);
	}

}
