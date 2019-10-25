package com.servicepartner.one.workforce.optimization.model;

import java.util.List;

public class OptimalResponse {
	
	List<StaffingLevel> optimalSolution;
	
	public OptimalResponse() {
		super();
	}

	public List<StaffingLevel> getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(List<StaffingLevel> optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

}
