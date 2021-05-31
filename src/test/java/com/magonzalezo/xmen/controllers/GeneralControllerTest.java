package com.magonzalezo.xmen.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magonzalezo.xmen.daos.IStatDao;
import com.magonzalezo.xmen.dtos.DnaDto;
import com.magonzalezo.xmen.dtos.StatOutcomeDto;

@SpringBootTest
class GeneralControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private IStatDao statDao;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objMapper;
	
	@BeforeEach
    void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objMapper = new ObjectMapper();
	}

	@Test
	void shouldReturnFalseWhenNoMutant() throws Exception{
		
		DnaDto dnaDto = new DnaDto();
		dnaDto.setDna(new String[]{"AGAGAG","TCTCTC","AGAGAG","TCTCTC","AGAGAG","TCTCTC"});
		
		mockMvc.perform(
					post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(dnaDto)))
				.andExpect(status().isForbidden());
	}

	@Test
	void shouldReturnTrueIfMutant() throws Exception{
		
		DnaDto dnaDto = new DnaDto();
		dnaDto.setDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		
		mockMvc.perform(
					post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(dnaDto)))
				.andExpect(status().isOk());
	}	

	@Test
	void shouldReturnHttpOkGettingStats() throws Exception {
		
		statDao.deleteAll();
		
		mockMvc.perform(
				get("/stats")
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(objMapper.writeValueAsString(new StatOutcomeDto(0, 0, 0))))
			.andExpect(status().isOk());
	}

}
