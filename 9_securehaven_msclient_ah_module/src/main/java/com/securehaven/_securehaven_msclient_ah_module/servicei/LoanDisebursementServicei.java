package com.securehaven._securehaven_msclient_ah_module.servicei;

import com.securehaven._securehaven_msclient_ah_module.model.Loan_Disbursement;

public interface LoanDisebursementServicei {

	public void saveDisbursementDetails(Loan_Disbursement ld);

	public Loan_Disbursement getdisbursedetails(int app_id);

}
