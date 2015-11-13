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
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.InconsistentDBException;
import database.ServicesDB.InvalidGoalException;

/**
 *
 * @author Luiza
 */
public interface AdminService extends EmployeeService {

	public void warnRepairResource(int resourceID, int employeeID) throws SQLException, ConnectionException, LicenceException, DataNotFoundException;

    public List<Room> listAllRooms() throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException;

    public List<Employee> listAllEmployees() throws SQLException, ConnectionException, DataNotFoundException;
    
    public List<Resource> listAllResources() throws SQLException, ConnectionException;

    public boolean createResource(String name, String type, float consumption, int roomID) throws SQLException, ConnectionException;

    public boolean createEmployee(String name, String cpf, String email, String password,
            int roomID, int isAdmin) throws SQLException, ConnectionException;

    public boolean promoteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;
    
    void updateEmployee(Employee e) throws SQLException, ConnectionException;

    boolean createRoom(int roomNumber, float creditAmount, int dailyGoalID)
            throws SQLException, ConnectionException, DataNotFoundException;

    boolean createGoal(Date day, float value) throws SQLException, ConnectionException, InvalidGoalException;

    boolean deleteResource(int resourceID) throws SQLException, ConnectionException;

    boolean deleteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;

    boolean deleteRoom(int roomID) throws SQLException, ConnectionException;

    boolean deleteGoal(int goalID) throws SQLException, ConnectionException;
    
    Employee findEmployee(int id) throws SQLException, ConnectionException, DataNotFoundException;
    
    Resource findResource(int id) throws SQLException, ConnectionException, DataNotFoundException;
    
    Room findRoom(int id) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException;
    
    void updateResource(Resource r) throws SQLException, ConnectionException;

}
