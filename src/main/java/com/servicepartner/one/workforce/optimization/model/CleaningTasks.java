package com.servicepartner.one.workforce.optimization.model;

import javax.validation.constraints.Positive;

public class CleaningTasks {
	
    private Integer[] rooms;
    @Positive(message = "Senior must be a positive number")
    private Integer senior = 0;
    @Positive(message = "Junior must be a positive number")
    private Integer junior = 0;
    
    public CleaningTasks() {
    	super();
	}

	public Integer[] getRooms() {
		return rooms;
	}

	public void setRooms(Integer[] rooms) {
		this.rooms = rooms;
	}

	public int getSenior() {
		return senior;
	}

	public void setSenior(int senior) {
		this.senior = senior;
	}

	public int getJunior() {
		return junior;
	}

	public void setJunior(int junior) {
		this.junior = junior;
	}
    
    


}
