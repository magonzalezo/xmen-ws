package com.magonzalezo.xmen.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stats", schema="mutantdatabase")
public class StatEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="dna_request")
	private String dnaRequest;
	
	@Column(name="mutant_checking_result")
	private boolean mutantCheckingResult;
	
	@Column(name="request_date")
	private Date requestDate;
	
	public StatEntity() {};

	public StatEntity(String dnaRequest, boolean mutantCheckingResult, Date requestDate) {
		super();
		this.dnaRequest = dnaRequest;
		this.mutantCheckingResult = mutantCheckingResult;
		this.requestDate = requestDate;
	}

	public long getId() {
		return id;
	}

	public void setDnaRequest(String dnaRequest) {
		this.dnaRequest = dnaRequest;
	}

	public boolean isMutantCheckingResult() {
		return mutantCheckingResult;
	}

	public void setMutantCheckingResult(boolean mutantCheckingResult) {
		this.mutantCheckingResult = mutantCheckingResult;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}	

}
