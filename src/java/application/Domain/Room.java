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
        this.dailyGoal = new Goal(new Date(), 1000);
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
            List<Resource> resourceList, List<Employee> resourceEmployee) {
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
}
