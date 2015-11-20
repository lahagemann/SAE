/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;


import java.util.ArrayList;
import java.util.List;

import database.ServicesDB.ReportOnOffDB;

import application.Impl.AdminServiceImpl;
import application.Interface.AdminService;


/*
 * @author Luiza
 */

public class Room {

    private List<Resource> resourceList;
    private List<Employee> employeeList;
    private final int identifier;
    private float creditAmount;
    private String name;
    private Goal dailyGoal;

    public Room(String name, Goal dailyGoal) {
        super();
        this.resourceList = new ArrayList<Resource>();
        this.employeeList = new ArrayList<Employee>();
        this.identifier = 0;
        this.dailyGoal = dailyGoal;
        this.creditAmount = dailyGoal.getValue();
        this.name = name;
    }

    public Room(String name, float creditAmount, Goal dailyGoal) {
        super();
        this.resourceList = new ArrayList<Resource>();
        this.employeeList = new ArrayList<Employee>();
        this.identifier = 0;
        this.dailyGoal = dailyGoal;
        this.creditAmount = dailyGoal.getValue();
        this.name = name;
    }

    public Room(int id, String name, float creditAmount, Goal dailyGoal,
            List<Resource> resourceList, List<Employee> resourceEmployee) {
        super();
        this.identifier = id;
        this.resourceList = resourceList;
        this.employeeList = resourceEmployee;
        this.dailyGoal = dailyGoal;
        this.creditAmount = creditAmount;
        this.name = name;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getIdentifier() {
        return identifier;
    }

    public float getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(float creditAmout) {
        this.creditAmount = creditAmout;
    }

    public Goal getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(Goal DailyGoal) {
        dailyGoal = DailyGoal;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addResource(Resource element) {
        this.resourceList.add(element);
    }

    public void removeResource(int position) {
        this.resourceList.remove(position);
    }

    public void addEmployee(Employee element) {
        this.employeeList.add(element);
    }

    public void removeEmployee(int position) {
        this.employeeList.remove(position);
    }

    public float expendCredits(float amount) {
        this.creditAmount -= amount;
        return this.creditAmount;
    }

    public boolean equals(Object other) {
        if (other instanceof Room) {
            if (((Room) other).getIdentifier() == this.getIdentifier()) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String args[]){
    	try{
    		AdminService aS = new AdminServiceImpl();
    		List<TurnOnOrOffReport> reports = aS.reportsOfDay("08/11/2015");
    		
    		for(TurnOnOrOffReport t : reports){
    			System.out.println(t.toString());
    		}
    		
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    } 
    
}
