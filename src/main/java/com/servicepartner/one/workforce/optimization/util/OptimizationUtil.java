package com.servicepartner.one.workforce.optimization.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servicepartner.one.workforce.optimization.model.StaffingLevel;

public final class OptimizationUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(OptimizationUtil.class);
	
	private OptimizationUtil(){
	}
	
    public static int greatestCommonDivisor(int a, int b) {
        if (b==0){
        	logger.debug("greatestCommonDivisor {}",a);
            return a;
        }
        return greatestCommonDivisor(b,a%b);
    }

    public static StaffingLevel exploreParams(int priorDistance, int buildingSize, int minInterval, int minStep, int seniorCapacity, int juniorCapacity, int priorSeniors, int currSeniors, int priorJuniors, int currJuniors){
        
        int currentStaffCapacity = (seniorCapacity * currSeniors) + (juniorCapacity * currJuniors);
        int distance = Math.abs(currentStaffCapacity-buildingSize);
        
        logger.debug("currentStaffCapacity {}",currentStaffCapacity);
        logger.debug("distance {}",distance);
        if(distance <= minInterval && currentStaffCapacity >= buildingSize){
            // We can't take a smaller step downwards and capacity has been filled
            return new StaffingLevel(currSeniors,currJuniors); //This is a good result
        }
        
        if(distance > minStep && currentStaffCapacity < buildingSize){
            // We have taken too many steps downwards and can't get back to buildingCapacity in one step
            // So backtrack and return values from previous iteration
            return new StaffingLevel(priorSeniors,priorJuniors);
        }
        
        
        if(currentStaffCapacity>buildingSize && currSeniors > 1){
            //We have too many staff assigned so remove a senior (if possible) and test again
            return exploreParams(distance,buildingSize, minInterval, minStep, seniorCapacity, juniorCapacity, currSeniors, currSeniors-1, currJuniors, currJuniors);
        }
        
        if(currentStaffCapacity<buildingSize){
            //We don't have enough staff assigned so add a junior and test again
            return exploreParams(priorDistance,buildingSize, minInterval, minStep, seniorCapacity, juniorCapacity, currSeniors, currSeniors, currJuniors, currJuniors+1);
        }
        
        return new StaffingLevel(currSeniors,currJuniors);
    }
}
