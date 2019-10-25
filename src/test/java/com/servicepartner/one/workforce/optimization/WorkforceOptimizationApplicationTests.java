package com.servicepartner.one.workforce.optimization;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
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
}
