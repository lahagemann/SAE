/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Interface;

import application.Domain.Employee;
import application.Domain.Resource;
import application.Domain.Room;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface AdminService extends EmployeeService {

    public void warnRepairResource(int resourceID);

    public List<Room> listAllRooms() throws SQLException, ConnectionException;

    public List<Employee> listAllEmployees() throws SQLException, ConnectionException;
    
    public List<Resource> listAllResources() throws SQLException, ConnectionException;

    public boolean createResource(String name, String type, float consumption, int roomID) throws SQLException, ConnectionException;

    public boolean createEmployee(String name, String cpf, String email, String password,
            int roomID, int isAdmin) throws SQLException, ConnectionException;

    public boolean promoteEmployee(int employeeID) throws SQLException, ConnectionException;
    
    void updateEmployee(Employee e) throws SQLException, ConnectionException;

    boolean createRoom(int roomNumber, float creditAmount, int dailyGoalID)
            throws SQLException, ConnectionException;

    boolean createGoal(Date day, float value) throws SQLException, ConnectionException;

    boolean deleteResource(int resourceID) throws SQLException, ConnectionException;

    boolean deleteEmployee(int employeeID) throws SQLException, ConnectionException;

    boolean deleteRoom(int roomID) throws SQLException, ConnectionException;

    boolean deleteGoal(int goalID) throws SQLException, ConnectionException;
    
    Employee findEmployee(int id) throws SQLException, ConnectionException;

}
