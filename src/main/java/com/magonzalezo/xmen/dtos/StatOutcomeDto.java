package com.magonzalezo.xmen.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatOutcomeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("count_mutant_dna")
	private long countMutantDna;
	
	@JsonProperty("count_human_dna")
	private long countHumanDna;
	
	@JsonProperty("ratio")
	private double ratio;
	
	public StatOutcomeDto() {};

	public StatOutcomeDto(long countMutantDna, long countHumanDna, double ratio) {
		super();
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = ratio;
	}

	public void setCountMutantDna(int countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public void setCountHumanDna(int countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}		
}
