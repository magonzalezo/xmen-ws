package com.magonzalezo.xmen.services.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magonzalezo.xmen.daos.IStatDao;
import com.magonzalezo.xmen.dtos.StatOutcomeDto;
import com.magonzalezo.xmen.entities.StatEntity;
import com.magonzalezo.xmen.services.IStatService;

@Service
public class StatService implements IStatService{
	
	private static final Logger log = LoggerFactory.getLogger(StatService.class);

	@Autowired
	private IStatDao statDao; 
	
	@Override
	public void saveVerifiedDna(boolean isMutant, String incomeDna) {
		log.debug("Income DNA: "+incomeDna);		
		statDao.save(new StatEntity(incomeDna, isMutant, new Date()));
	}

	@Override
	public StatOutcomeDto returnStats() {
		
		List<StatEntity> lstStats =  (List<StatEntity>)statDao.findAll();
		
		if (lstStats.isEmpty()) {
			log.debug("lstStats is empty");

			return new StatOutcomeDto(0, 0, 0);
		}
			
		long countMutants = lstStats.stream().filter(record -> record.isMutantCheckingResult()).count();
		long countHumans  = lstStats.stream().filter(record -> !record.isMutantCheckingResult()).count();
		double calculateRatio;
		
		calculateRatio = (double)countMutants / (double)countHumans;
		
		if (Double.isInfinite(calculateRatio))
			calculateRatio = 0;
	
		
		return new StatOutcomeDto(countMutants, countHumans, calculateRatio);
				
	}

}
