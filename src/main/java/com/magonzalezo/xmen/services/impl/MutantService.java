package com.magonzalezo.xmen.services.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magonzalezo.xmen.constants.General;
import com.magonzalezo.xmen.constants.Messages;
import com.magonzalezo.xmen.dtos.DnaDto;
import com.magonzalezo.xmen.predicates.MutantPredicate;
import com.magonzalezo.xmen.predicates.OnlyLetterDnaPredicate;
import com.magonzalezo.xmen.predicates.UppercaseDnaPredicate;
import com.magonzalezo.xmen.predicates.ValidCaracterDnaPredicate;
import com.magonzalezo.xmen.services.IMutantService;
import com.magonzalezo.xmen.services.IStatService;
import com.magonzalezo.xmen.utils.Util;

@Service
public class MutantService implements IMutantService{
	
	private static final Logger log = LoggerFactory.getLogger(MutantService.class);
	
	@Autowired
	private IStatService statService;
	
	@Override
	public boolean isMutant(DnaDto dnaDto) {
		
		if (!isValidDnaSequenceIncome(dnaDto.getDna()))
			return false;
		
		boolean isMutantFound = hasMoreThanOneMatch(dnaDto.getDna());
		
		statService.saveVerifiedDna(isMutantFound, Arrays.toString(dnaDto.getDna()));
		
		return isMutantFound;
	}
	
	private boolean isValidMatrix(String[] dnaSequence) {
		
		if (   null == dnaSequence
			|| dnaSequence.length == 0)
			return false;
		
		int minLengthOfSymmetricalMatrix = General.MIN_CHARACTERS_PER_RECORD * General.MIN_CHARACTERS_PER_RECORD;
		int totalLengthIncome = 0;
		StringBuilder linealSequence = new StringBuilder();
		
		for(String record : dnaSequence) {
			totalLengthIncome += record.length();
			linealSequence.append(record);
		}		
		
		log.debug("Original linear sequence: "+linealSequence.toString());
		
		int rowsInMatrix = dnaSequence[0].length();
		
		if (   totalLengthIncome < minLengthOfSymmetricalMatrix 
			|| (totalLengthIncome != (rowsInMatrix * rowsInMatrix))) {
			return false;
		}
		
		return true;
	}
	
	private boolean isValidDnaSequenceIncome(String[] dnaSequence) {
		
		if (!isValidMatrix(dnaSequence)) {
			log.debug(Messages.NON_VALID_MATRIX);
			return false;
		}
		
		int rowsInMatrix = dnaSequence[0].length();
		
		if (Arrays.asList(dnaSequence).stream().filter(new OnlyLetterDnaPredicate()).count() < rowsInMatrix) {
			log.debug(Messages.ONLY_LETTERS_ALLOWED);
			return false;
		}
		
		if (Arrays.asList(dnaSequence).stream().filter(new UppercaseDnaPredicate()).count() < rowsInMatrix) {
			log.debug(Messages.ONLY_UPPERCASE_LETTERS_ALLOWED);
			return false;
		}
		
		if (Arrays.asList(dnaSequence).stream().filter(new ValidCaracterDnaPredicate()).count() < rowsInMatrix) {
			log.debug(Messages.CHARACTERS_REQUIRED_NOT_FOUND);
			return false;
		}
		
		return true;
	}
	
	private boolean hasMoreThanOneMatch(String[] dnaOriginalSequence) {
		
		long coincidenceCounter = 0L;
		MutantPredicate mutantPredicate = new MutantPredicate();
				
		// Check horizontal values
		coincidenceCounter = Arrays.asList(dnaOriginalSequence).stream().filter(mutantPredicate).count();
		
		log.debug("Coincidences until horizontal matrix: "+coincidenceCounter);

		if (coincidenceCounter > 1) 
			return true;
		
		// Check vertical values
		String[] dnaVerticalSequence = Util.getTransposedData(dnaOriginalSequence);
		
		StringBuilder linealSequence = new StringBuilder();
		
		for(String record : dnaVerticalSequence) {
			linealSequence.append(record);
		}
		
		log.debug("Vertical sequence: "+linealSequence.toString());
		
		linealSequence.setLength(0);
		
		coincidenceCounter += Arrays.asList(dnaVerticalSequence).stream().filter(mutantPredicate).count();
		
		log.debug("Coincidences until vertical matrix: "+coincidenceCounter);
		
		if (coincidenceCounter > 1) 
			return true;
		
		// Check left oblique values
		String[] dnaObliqueLeftSequence = Util.getObliqueLeftData(dnaOriginalSequence);
		
		for(String record : dnaObliqueLeftSequence) {
			linealSequence.append(record);
		}
		
		log.debug("Left oblique sequence: "+linealSequence.toString());
		
		linealSequence.setLength(0);
		
		coincidenceCounter += Arrays.asList(dnaObliqueLeftSequence).stream().filter(mutantPredicate).count();
		
		log.debug("Coincidences until left oblique matrix: "+coincidenceCounter);
		
		if (coincidenceCounter > 1) 
			return true;
		
		// Check left oblique values
		String[] dnaObliqueRightSequence = Util.getObliqueRightData(dnaOriginalSequence);
		
		for(String record : dnaObliqueRightSequence) {
			linealSequence.append(record);
		}
		
		log.debug("Right oblique sequence: "+linealSequence.toString());
		
		linealSequence.setLength(0);
		
		coincidenceCounter += Arrays.asList(dnaObliqueRightSequence).stream().filter(mutantPredicate).count();
		
		log.debug("Coincidences until right oblique matrix: "+coincidenceCounter);
		
		if (coincidenceCounter > 1) 
			return true;
		
		return false;
		
	}

}
