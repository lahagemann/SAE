/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;

import java.util.Date;

/**
 *
 * @author Luiza
 */
public class Goal {

	
	private Date day;
	private float value;
	private final int identifier;
	
	public Goal(Date day, float value) {
		super();
		this.day = day;
		this.value = value;
		this.identifier = 1;
	}
        
        public Goal(int identifier, Date day, float value) {
		super();
		this.day = day;
		this.value = value;
		this.identifier = identifier;
	}
	
	public Date getDay() {
		return day;
	}
	
	public int getIdentifier() {
		return identifier;
	}

	public void setDay(Date day) {
		this.day = day;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}

}
