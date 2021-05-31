package com.magonzalezo.xmen.dtos;

public class DnaDto {
	
	private String[] dna;
	
	public DnaDto() {}

	public DnaDto(String[] dna) {
		super();
		this.dna = dna;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}	
		
}
