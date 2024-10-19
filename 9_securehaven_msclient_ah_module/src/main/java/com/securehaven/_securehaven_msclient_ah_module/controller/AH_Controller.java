package com.securehaven._securehaven_msclient_ah_module.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.securehaven._securehaven_msclient_ah_module.model.Customer_Loan_Application;
import com.securehaven._securehaven_msclient_ah_module.model.Loan_Disbursement;
import com.securehaven._securehaven_msclient_ah_module.model.Saction_Letter;
import com.securehaven._securehaven_msclient_ah_module.model.Success;
import com.securehaven._securehaven_msclient_ah_module.servicei.LoanDisebursementServicei;

@CrossOrigin
@RestController
public class AH_Controller{
	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	LoanDisebursementServicei ldbi;
	
	@GetMapping("/get_sanction_loan_details")
	public ResponseEntity<List<Customer_Loan_Application>> getSanctionLetter() {
		String url="http://localhost:9099/forward_sanction_details";
		Saction_Letter[] sl =	rt.getForObject(url, Saction_Letter[].class);
		
		List<Saction_Letter> list = new ArrayList<Saction_Letter>(Arrays.asList(sl));
		
		String url2 = "http://localhost:9092/forwardto_ah";
		
		Customer_Loan_Application[] cla =rt.postForObject(url2, list, Customer_Loan_Application[].class);
		
		List<Customer_Loan_Application> list2 = new ArrayList<Customer_Loan_Application>(Arrays.asList(cla));
		
		return new ResponseEntity<List<Customer_Loan_Application>>(list2,HttpStatus.OK);
	}
	
	@GetMapping("/get_sanction_details/{app_id}")
	public ResponseEntity<Saction_Letter> getsanctiondetails(@PathVariable("app_id") int app_id)
	{
		String url="http://localhost:9099/forward_single_sanction_details/"+app_id;
		Saction_Letter sl =	rt.getForObject(url, Saction_Letter.class);
		
		
		return new ResponseEntity<Saction_Letter>(sl, HttpStatus.OK);
	}
	
	@PostMapping("/save_disburse_details")
	public ResponseEntity<Success> saveDisburse(@RequestBody Loan_Disbursement ld)
	{
		
		int min = 200000;
		int max = 888888;

		int f = min + (int) (Math.random() * ((max - min) + 1));
		
		ld.setAggrementDate(new Date());
		ld.setLoanNumber(f);
		
		ldbi.saveDisbursementDetails(ld);
		
		Success ss = new Success("Details saved successfully");
		
		return new ResponseEntity<Success>(ss,HttpStatus.OK);
	}
	
	@GetMapping("/forward_loan_status/{app_id}")
	public ResponseEntity<Loan_Disbursement> forwordloanstatus(@PathVariable("app_id") int app_id)
	{
		
		Loan_Disbursement ld = ldbi.getdisbursedetails(app_id);
		
		return new ResponseEntity<Loan_Disbursement>(ld,HttpStatus.OK);
	}

}
