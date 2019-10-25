package com.servicepartner.one.workforce.optimization.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.servicepartner.one.workforce.optimization.model.CleaningTasks;
import com.servicepartner.one.workforce.optimization.model.StaffingLevel;

@Service
public interface OptimizerService {

	List<StaffingLevel> optimiseStaff(CleaningTasks tasks);
	StaffingLevel optimiseForBuilding(int buildingSize, int seniorCapacity, int juniorCapacity);
}
