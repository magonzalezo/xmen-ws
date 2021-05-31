package com.magonzalezo.xmen.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magonzalezo.xmen.daos.IStatDao;
import com.magonzalezo.xmen.dtos.StatOutcomeDto;
import com.magonzalezo.xmen.entities.StatEntity;
import com.magonzalezo.xmen.services.IStatService;

@SpringBootTest
class StatServiceTest {
	
	@Autowired
	private IStatService statService;
	
	@Autowired
	private IStatDao statDao;
	
	@Test
	void shouldReturnTrueStatQueryNoRecords() {
		statDao.deleteAll();
		List<StatEntity> lstStats =  (List<StatEntity>)statDao.findAll();
		System.out.println("Cantidad datos: "+lstStats.size());
		assertTrue(lstStats.isEmpty());		
	}
	
	@Test
	void shouldReturnTrueWhenRecordSaved() {
		StatEntity statEntity = new StatEntity();
		statDao.save(statEntity);
		List<StatEntity> lstStats = (List<StatEntity>)statDao.findAll();
		assertTrue(lstStats.size()>0);
		
	}	
	
	@Test
	void shouldReturnNotNullStatObject() {
		StatOutcomeDto statOutcomeDto = statService.returnStats();
		assertNotNull(statOutcomeDto);
	}
	
	@Test
	void shouldReturnNotNullStatObjectWhenEmptyTable() {
		statDao.deleteAll();
		StatOutcomeDto statOutcomeDto = statService.returnStats();
		assertNotNull(statOutcomeDto);
	}
	
	@Test
	void shouldReturnTrueWhenRationGreaterThanZeroWhenManyValuesHumanAndMutant() {
		statDao.deleteAll();
		String[] dnaFakeSequence = {"ATGCAA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
				
		for (int i = 1; i<=10; i++) {
			StatEntity statEntity = new StatEntity();
			statEntity.setMutantCheckingResult(new Random().nextBoolean());
			statEntity.setRequestDate(new Date());
			statEntity.setDnaRequest(Arrays.toString(dnaFakeSequence));
			statDao.save(statEntity);
		}
		
		StatOutcomeDto statOutcomeDto = statService.returnStats();
		assertTrue( statOutcomeDto.getRatio() > 0 );
	}
	
	@Test
	void shouldReturnWhenRatioValueIsInfinite() {
		statDao.deleteAll();
		String[] dnaFakeSequence = {"ATGCAA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
				
		StatEntity statEntity = new StatEntity();
		statEntity.setMutantCheckingResult(true);
		statEntity.setRequestDate(new Date());
		statEntity.setDnaRequest(Arrays.toString(dnaFakeSequence));
		statDao.save(statEntity);
	
		StatOutcomeDto statOutcomeDto = statService.returnStats();
		assertTrue( statOutcomeDto.getRatio() == 0 );
	}
	
	@Test
	void shouldReturnNotNullWhenCreateFaceStatOutcomeDto() {
		
		StatOutcomeDto statOutcomeDto = new StatOutcomeDto();
		statOutcomeDto.setCountHumanDna(0);
		statOutcomeDto.setCountMutantDna(0);
		statOutcomeDto.setRatio(0);
		
		assertNotNull(statOutcomeDto.getRatio()==0);
	}

}
