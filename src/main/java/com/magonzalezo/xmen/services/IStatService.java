package com.magonzalezo.xmen.services;

import com.magonzalezo.xmen.dtos.StatOutcomeDto;

public interface IStatService {
	
	public void saveVerifiedDna(boolean isMutant, String incomeDna);
	
	public StatOutcomeDto returnStats();

}
