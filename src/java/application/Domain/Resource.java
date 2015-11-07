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
public class Resource {

	
	private final int identifier;
	private String name;
        private String type;
	private float consumption;
	private boolean resourceOn;
	private Date turnOnTime;
	private boolean resourceBroken;
	private int locationID;
	
	public Resource(String name, String type, float d, int locationID) {
		super();
		
		resourceOn = false;
		identifier = 0;
		turnOnTime = null;
		resourceBroken = false;
		
		this.name = name;
         this.type = type;
		this.consumption = d;
		this.locationID = locationID;
	}
        
       public Resource(  String name,int identifier,
			String type, float consumption, boolean resourceOn,
			boolean resourceBroken, Date turnOnTime, int locationID) {
		super();
		this.resourceOn = resourceOn;
		this.identifier = identifier;
		this.name = name;
		this.type = type;
		this.consumption = consumption;
		// esperar a jessica implementar o date
		this.turnOnTime = turnOnTime; 
		this.resourceBroken = resourceBroken;
		this.locationID = locationID;
	}



       public Resource(String name,
   			String type, float consumption, boolean resourceOn,
   			boolean resourceBroken, Date turnOnTime, int locationID) {
		super();
		this.identifier = 0; 
		this.resourceOn = resourceOn;
		this.name = name;
		this.type = type;
		this.consumption = consumption;
		this.turnOnTime = turnOnTime; 
		this.resourceBroken = resourceBroken;
		this.locationID = locationID;
	}



	public Resource(String name, int identifier, String type, float consumption, int location) {
		super();
		
		this.resourceOn = false;
		this.identifier = identifier;
		this.turnOnTime = null;
		this.resourceBroken = false;
		
		this.name = name;
		this.type = type;
		this.consumption = consumption;
		this.locationID = location;
	}
                     
	public boolean isOn() {
		return resourceOn;
	}
	
	public int isOnINT() {
		if(this.resourceOn) return 1;
		else return 0;
	}
	
	public int getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
	
	public Date getTurnOnTime() {
		return turnOnTime;
	}
	
	public void setTurnOnTime(Date turnOnTime) {
		this.turnOnTime = turnOnTime;
	}
	
	public float getConsumption() {
		return consumption;
	}
	
	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}
	
	public boolean isResourceBroken() {
		return resourceBroken;
	}
	
	public int isResourceBrokenINT() {
		if(this.resourceBroken) return 1;
		else return 0;
	}

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
	
	public void turnOn(){
		this.resourceOn = true;
		this.turnOnTime = new Date();
	}
	
	public float turnOff(){
		if(!this.isOn()){   
			return 0;
		}
		else{
			this.resourceOn = false;
			float tmp =  this.getConsumption()*calculateConsumption(this.turnOnTime, new Date());
			this.turnOnTime = null;
			return tmp;
		}
	}
	
	public void warnFlaw(){
		this.resourceBroken = true;
	}
	
	public void warnRepair(){
		this.resourceBroken = false;
	}
	
	public void removeLocation(){
		this.locationID = 0;
	}
	
	private static float calculateConsumption(Date on, Date off){
		
		System.out.println(on.toString() + " " + off.toString());
		
		long different = off.getTime() - on.getTime();
		
		long minutesInMilli = 1000 * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;
		
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		
		long elapsedMinutes = different / minutesInMilli;
		
		return (float)(elapsedDays*24+elapsedHours+(float)elapsedMinutes/60);	
	}
	
}
