package com.servicepartner.one.workforce.optimization.model;

public class StaffingLevel {

	private int senior;
	private int junior;

	public StaffingLevel() {
		super();
	}

	public StaffingLevel(int senior, int junior) {
		super();
		this.senior = senior;
		this.junior = junior;
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

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 47 * hash + this.senior;
		hash = 47 * hash + this.junior;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StaffingLevel other = (StaffingLevel) obj;
		
		return ((this.senior == other.senior)||(this.junior == other.junior));
	}

}
