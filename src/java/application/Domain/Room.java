/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;

import database.ServicesDB.CustomActionDB;
import database.ServicesDB.EmployeeDB;
import database.ServicesDB.GoalDB;
import database.ServicesDB.ResourceDB;
import database.ServicesDB.RoomDB;
import database.ServicesDBImpl.CustomActionDBImpl;
import database.ServicesDBImpl.EmployeeDBImpl;
import database.ServicesDBImpl.GoalDBImpl;
import database.ServicesDBImpl.ResourceDBImpl;
import database.ServicesDBImpl.RoomDBImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.Impl.EmployeeServiceImpl;
import application.Interface.EmployeeService;
import database.Connection.ConnectionException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luiza
 */
public class Room {
	
	private List<Resource> resourceList;
	private List<Employee> employeeList;
	private final int identifier;
	private float creditAmount;
    private int roomNumber;
    private Goal dailyGoal;
	
	public Room(int roomNumber) {
		super();
		this.resourceList = new ArrayList<Resource>();
		this.employeeList = new ArrayList<Employee>();
		this.identifier = 0;
		this.dailyGoal = new Goal( new Date(), 1000);
		this.creditAmount = dailyGoal.getValue();
		this.roomNumber = roomNumber;
	}
        
	public Room(int roomNumber, float creditAmount, Goal dailyGoal) {
		super();
		this.resourceList = new ArrayList<Resource>();
		this.employeeList = new ArrayList<Employee>();
		this.identifier = 0;
		this.dailyGoal = dailyGoal;
		this.creditAmount = dailyGoal.getValue();
		this.roomNumber = roomNumber;
	}
	
	public Room(int id, int roomNumber, float creditAmount, Goal dailyGoal,
                List<Resource> resourceList, List<Employee> resourceEmployee ) {
		super();
        this.identifier = id;
		this.resourceList = resourceList;
		this.employeeList = resourceEmployee;
        this.dailyGoal = dailyGoal;
		this.creditAmount = creditAmount;
        this.roomNumber = roomNumber;
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
        
        public int getRoomNumber() {
            return roomNumber;
        }
        
        public void setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
        }
	
	public void addResource(Resource element){
		this.resourceList.add(element);
	}
	
	public void removeResource(int position){
		this.resourceList.remove(position);
	}
	
	public void addEmployee(Employee element){
		this.employeeList.add(element);
	}
	
	public void removeEmployee(int position){
		this.employeeList.remove(position);
	}
	
	public float expendCredits(float amount){
		this.creditAmount -= amount;
		return this.creditAmount;
	}
	
	public boolean equals(Object other){
		if(other instanceof Room){
			if(((Room) other).getIdentifier() == this.getIdentifier()) return true;
		}
		return false;
	}

        public static void main(String args[]) {

    
            
            EmployeeDB db1 = new EmployeeDBImpl();
            ResourceDB db2 = new ResourceDBImpl();
            RoomDB db3 = new RoomDBImpl();
            GoalDB db4 = new GoalDBImpl();
            CustomActionDB db5 = new CustomActionDBImpl();
            Employee b = new Employee("Gustavo Dambros","121212121", "gvdambros2", "1234",1, 0);
            Resource c = new Resource("Ar do Gustavo", "Ar Condicionado", (float) 10000.5,1);
            Room d = new Room(13);
            Goal e = new Goal(new Date(2020,3,3), 80);
            EmployeeService s = new EmployeeServiceImpl();
            //Admin f = new Admin("Gustavo 2","12345678910", "gvdambros", "1234",1);
            //Employee g = new Admin("Gustavo 3","12345678910", "gvdambros", "1234",1);
            List<Resource> listOfResources = new ArrayList<Resource>();
            try{
                //listOfResources.addAll(db2.findResourceByRoom(1)); 
                CustomAction h = new CustomAction(1, "gustavo legal", listOfResources);
                db5.insertCustomAction(h);
                //System.out.println("Aqui");
                //db1.insertEmployee(f);
                //db1.insertEmployee(g);
                //db3.insertRoom(d);
                //db2.insertResource(c);
                //db3.deleteRoom(1);
                //s.login("gvdambros","b");
                db4.insertGoal(e);
                
                //db1.promoteEmployee(2);
                
                //db1.promoteEmployee(1);
                //System.out.println(db4.findLastGoal().getDay().toString());
                //db3.insertRoom(d);
                
                Goal test;
                
                //test  = db4.findGoal(2);
                //test.setValue(45);
                //db4.deleteGoal(2);
                
                Room sala;
                sala = db3.findRoomByID(13);
              
                //sala.setDailyGoal(test);
                //db3.updateRoom(sala);
                
                System.out.println(sala);
               // System.out.println(test.getIdentifier());
                
                //for(int i = 0; i < l.size(); i++){
                	//System.out.println(l.get(i).toString());
                //}
                //System.out.println("Aqui");
                
            }
            catch(Exception exp){
                System.out.println(exp.getClass().toString() + " " + exp.getMessage());
            }
        }
        
}
