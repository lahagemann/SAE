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
public class TurnOnOrOffReport extends Transaction {
	
	public String resourceName, roomName;
	
	public TurnOnOrOffReport(int transactionResourceID,String roomName, Date initialTime,
			Date finalTime) {
		super(transactionResourceID, initialTime, finalTime);
		this.resourceName = null;
		this.roomName = roomName;
	}

	public TurnOnOrOffReport(int transactionResourceID, String roomName, Date initialTime) {
		super(transactionResourceID, initialTime);
		this.resourceName = null;
		this.roomName = roomName;
	}

	public TurnOnOrOffReport(int transactionResourceID, String roomName) {
		super(transactionResourceID);
		this.resourceName = null;
		this.roomName = roomName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String toString(){
		return "Recurso: " + getResourceName() + "\nSala: " + getRoomName() + "\nLigou: " + getInitialTime() + "\nDesligou: " + getFinalTime();  
		
	}
	
}
