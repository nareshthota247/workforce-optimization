package com.servicepartner.one.workforce.optimization.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.servicepartner.one.workforce.optimization.model.CleaningTasks;
import com.servicepartner.one.workforce.optimization.model.StaffingLevel;
import com.servicepartner.one.workforce.optimization.util.OptimizationUtil;

@Service
public class OptimizerServiceImpl implements OptimizerService {

	@Override
	public List<StaffingLevel> optimiseStaff(CleaningTasks tasks) {
		
		 List<StaffingLevel> staffingLevels = new ArrayList<>();
	        
	        for(int roomCount : tasks.getRooms()){
	            staffingLevels.add(optimiseForBuilding(roomCount,tasks.getSenior(),tasks.getJunior()));
	        }
	        
	        return staffingLevels;
	}
	
	@Override
    public StaffingLevel optimiseForBuilding(int buildingSize, int seniorCapacity, int juniorCapacity){

        //Setup our starting parameters
        int startSeniors =  (int)Math.ceil((double)buildingSize/(double)seniorCapacity); //What if we only used seniors to clean?
        int startJuniors = 0; //And no juniors?
        
        int minOffset = OptimizationUtil.greatestCommonDivisor(seniorCapacity,juniorCapacity);
        int minStepSize = Math.min(seniorCapacity, juniorCapacity);
        
        return OptimizationUtil.exploreParams(Integer.MAX_VALUE,buildingSize,minOffset,minStepSize,seniorCapacity,juniorCapacity,startSeniors,startSeniors,startJuniors,startJuniors);
        
    }
    
   
    
    
    
    
}
