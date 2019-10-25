package com.servicepartner.one.workforce.optimization.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CleaningTasks {
	
	@Size(min=1, max=100)
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

	@NotNull(message = "Senior may not be null")
	public int getSenior() {
		return senior;
	}

	public void setSenior(int senior) {
		this.senior = senior;
	}

	@NotNull(message = "Junior may not be null")
	public int getJunior() {
		return junior;
	}

	public void setJunior(int junior) {
		this.junior = junior;
	}
    
    


}
