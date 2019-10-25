package com.servicepartner.one.workforce.optimization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicepartner.one.workforce.optimization.model.CleaningTasks;
import com.servicepartner.one.workforce.optimization.model.StaffingLevel;
import com.servicepartner.one.workforce.optimization.service.OptimizerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class WorkforceOptimizationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private OptimizerService optimizerService;
	
//	@Test
//	void contextLoads() {
//	}

	@Test
	public void test1OptimiseingBuilding() throws Exception {
		StaffingLevel staffingLevel = optimizerService.optimiseForBuilding(35, 10, 6);
		assertThat(staffingLevel).isEqualTo(new StaffingLevel(3, 1));
	}
	
	@Test
	public void test2OptimiseingBuilding() throws Exception {
		StaffingLevel staffingLevel = optimizerService.optimiseForBuilding(21, 10, 6);
		assertThat(staffingLevel).isEqualTo(new StaffingLevel(1, 2));
	}
	
	@Test
	public void when_NegitiveSeniorthenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{35, 21, 17, 28};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(-8);
		ct.setJunior(6);
		 
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.senior").value("Senior must be a positive number")).andReturn();
	}
	
	@Test
	public void when_ZeroSeniorthenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{35, 21, 17, 28};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(0);
		ct.setJunior(6);
		 
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.senior").value("Senior must be a positive number")).andReturn();
	}
	
	@Test
	public void when_NegitiveJuniorthenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{35, 21, 17, 28};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(8);
		ct.setJunior(-6);
		  
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.junior").value("Junior must be a positive number")).andReturn();
	}
	
	@Test
	public void when_ZeroJuniorthenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{35, 21, 17, 28};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(8);
		ct.setJunior(0);
		  
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.junior").value("Junior must be a positive number")).andReturn();
	}
	
	@Test
	public void when_ZeroRoomSizethenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{0, 21, 17, 28};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(8);
		ct.setJunior(6);
		  
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errorDesc").value("Room size cannot be Zero or Negitive")).andReturn();
	}
	
	@Test
	public void when_NegitiveRoomSizethenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{-35, 21, 17, 28};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(8);
		ct.setJunior(6);
		  
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errorDesc").value("Room size cannot be Zero or Negitive")).andReturn();
	}
	
	@Test
	public void when_ZeroRoomCountthenConstrainViolation() throws Exception {
		Integer[] rooms=new Integer[]{};
		
		CleaningTasks ct=new CleaningTasks();
		ct.setRooms(rooms);
		ct.setSenior(8);
		ct.setJunior(6);
		  
		mockMvc.perform(post("/api/optimize").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(ct)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.rooms").value("size must be between 1 and 100")).andReturn();
	}
	
}
