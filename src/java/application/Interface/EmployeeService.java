/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Interface;

import application.Domain.CustomAction;
import application.Domain.Employee;
import application.Domain.Resource;

import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface EmployeeService {

	public void turnOnResource(int resourceID, int employeeID) throws LicenceException, SQLException, ConnectionException;
        
    	public void turnOnResource(int resourceID) throws SQLException, ConnectionException;
	
	public void turnOffResource(int resourceID, int employeeID) throws LicenceException, SQLException, ConnectionException;
        
        public void turnOffResource(int resourceID) throws SQLException, ConnectionException;
	
	public void warnFlawResource(int resourceID);
	
	public List<Resource> listResorcesWorkRoom(int employeeID) throws SQLException, ConnectionException;
	
	public List<CustomAction> listCustomActions(int employeeID);
	
	public void runCustomAction(int employeeID, int customAction) throws InvalidCustomAction; // customAction é a posição do ação customizada na lista do empregado
	
	public void cancelCustomAction(int employeeID, int customAction) throws InvalidCustomAction;
	
	public Employee login(String email, String password) throws InvalidUserException, ConnectionException;
}
