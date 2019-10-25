package com.servicepartner.one.workforce.optimization.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.servicepartner.one.workforce.optimization.exceptionhandeling.RoomNegitiveException;
import com.servicepartner.one.workforce.optimization.model.CleaningTasks;
import com.servicepartner.one.workforce.optimization.model.StaffingLevel;
import com.servicepartner.one.workforce.optimization.util.OptimizationUtil;

@Service
public class OptimizerServiceImpl implements OptimizerService {

	private static final Logger logger = LoggerFactory.getLogger(OptimizerServiceImpl.class);

	@Override
	public List<StaffingLevel> optimiseStaff(CleaningTasks tasks) {
		
		 List<StaffingLevel> staffingLevels = new ArrayList<>();
	        
		 if(tasks.getRooms().length == 0)
			 throw new RoomNegitiveException("Room count cannot be Zero");
	        for(int roomCount : tasks.getRooms()){
	        	if(roomCount<=0) {
	        		throw new RoomNegitiveException("Room size cannot be Zero or Negitive");
	        	}
	            staffingLevels.add(optimiseForBuilding(roomCount,tasks.getSenior(),tasks.getJunior()));
	        }
	        
	        return staffingLevels;
	}
	
	@Override
    public StaffingLevel optimiseForBuilding(int buildingSize, int seniorCapacity, int juniorCapacity){

		logger.debug("buildingSize {}",buildingSize);
		logger.debug("seniorCapacity {}",seniorCapacity);
		logger.debug("juniorCapacity {}",juniorCapacity);
		
        int startSeniors =  (int)Math.ceil((double)buildingSize/(double)seniorCapacity);
        int startJuniors = 0;
        int minOffset = OptimizationUtil.greatestCommonDivisor(seniorCapacity,juniorCapacity);
        int minStepSize = Math.min(seniorCapacity, juniorCapacity);
        
        logger.debug("startSeniors {}",startSeniors);
        logger.debug("startJuniors {}",startJuniors);
        logger.debug("minOffset {}",minOffset);
        logger.debug("minStepSize {}",minStepSize);
        
        return OptimizationUtil.exploreParams(Integer.MAX_VALUE,buildingSize,minOffset,minStepSize,seniorCapacity,juniorCapacity,startSeniors,startSeniors,startJuniors,startJuniors);
        
    }
    
   
    
    
    
    
}
