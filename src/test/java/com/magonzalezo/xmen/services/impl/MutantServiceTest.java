package com.magonzalezo.xmen.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magonzalezo.xmen.dtos.DnaDto;
import com.magonzalezo.xmen.services.IMutantService;

@SpringBootTest
class MutantServiceTest {
	
	@Autowired
	private IMutantService generalService;
	
	@Test
	void shouldReturnFalseWhenDnaSequenceProvidesIsNull() {
		String[] dnaSequence = null;
		DnaDto dnaDto = new DnaDto();
		dnaDto.setDna(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenDnaSequenceProvidesIsEmpty() {
		String[] dnaSequence = {};
		DnaDto dnaDto = new DnaDto();
		dnaDto.setDna(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}

	@Test
	void shouldCreateDnaDtoFromEmptyConstructorAndPopulateAtributesOneByOne() {
		String[] dnaSequence = {"AGAGAG","TCTCTC","AGAGAG","TCTCTC","AGAGAG","TCTCTC"};
		DnaDto dnaDto = new DnaDto();
		dnaDto.setDna(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenDnaSequenceProvidedHasMenorLenght() {
		String[] dnaSequence = {"AGA","TCT","AGA"};
		DnaDto dnaDto = new DnaDto();
		dnaDto.setDna(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenNoCoincidences() {
		String[] dnaSequence = {"AGAGAG","TCTCTC","AGAGAG","TCTCTC","AGAGAG","TCTCTC"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenHasTwoCoincidences() {
		String[] dnaSequence = {"ATGCAA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenHasTwoOrMoreCoincidencesInHorizontal() {
		String[] dnaSequence = {"GTAAAA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenHasTwoOrMoreCoincidencesInVertical() {
		String[] dnaSequence = {"ACGCGA","CTGTGC","TTATGT","ATAAGG","CTCCTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenHasTwoOrMoreCoincidencesInLeftOblique() {
		String[] dnaSequence = {"ATGCGA","CAGTGC","TTATGT","ATAAGG","CCTCTA","TCATTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenHasTwoOrMoreCoincidencesInRightOblique() {
		String[] dnaSequence = {"CTGCGA","CAGTAC","TTAAGT","AGAAGG","CCCCTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenIsNotSymmetricMatrix() {
		String[] dnaSequence = {"ATGCGA","CAGT","TTATGT","AAGG","CCCCTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenMatrixHasNumbers() {
		String[] dnaSequence = {"ATGCGA","CAG8GC","TTATGT","AGAAGG","CC1CTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenMatrixHasSpecialCharacters() {
		String[] dnaSequence = {"ATGC#A","CAGTGC","TTATGT","A.AAGG","CCCCTA","T$ACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenMatrixHasLowercaseCharacters() {
		String[] dnaSequence = {"ATgcGA","CAGTGC","TTATGT","AGAAgg","CcCCTA","tCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenMatrixHasInvalidCharacters() {
		String[] dnaSequence = {"ATGCGA","CAGTGC","TTATPT","AGAAGG","JCCCTA","TCACTG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenSearchSequenceInGreaterMatrix() {		
		String[] dnaSequence = {"ATGCGAA","CAGTGCA","TTATGTG","AGAAGGC","CCCCTAA","TCACTGG","AGAAGTC"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnFalseWhenSearchSequenceInGreaterMatrixWithNoValidCharacters() {		
		String[] dnaSequence = {"ATGCGAAC","CAGTGCAG","TTATGTGT","AGAAGGCA","CCCCTAAG","TCACTGGC","AGAAGTCG","AGAAGYCG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertFalse(generalService.isMutant(dnaDto));
	}
	
	@Test
	void shouldReturnTrueWhenSearchSequenceInGreaterMatrixWithValidCharacters() {		
		String[] dnaSequence = {"ATGCGAAC","CAGTGCAG","TTATGTGT","AGAAGGCA","CCCCTAAG","TCACTGGC","AGAAGTCG","AGAAGCCG"};
		DnaDto dnaDto = new DnaDto(dnaSequence);
		assertTrue(generalService.isMutant(dnaDto));
	}

}
