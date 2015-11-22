/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDB;

import application.Domain.Employee;
import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface EmployeeDB {
    
    public void connect() throws SQLException, ConnectionException;
    
    public void disconnect() throws SQLException;
    
    public void insertEmployee(Employee e) throws SQLException, ConnectionException, DataNotFoundException;
    
    public void deleteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;
    
    public void updateEmployee(Employee e) throws SQLException, ConnectionException, DataNotFoundException;
    
    public List<Employee> getEmployeeList() throws SQLException, ConnectionException, DataNotFoundException;
    
    public Employee findEmployeeByID(int ID) throws SQLException, ConnectionException, DataNotFoundException;
    
    public List<Employee> findEmployeeByRoom(int idroom) throws SQLException, ConnectionException, DataNotFoundException;

	public Employee findEmployeeByEmail(String email) throws SQLException, ConnectionException, DataNotFoundException;
	
	public boolean promoteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;
}
