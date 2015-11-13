/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDB;

import application.Domain.CustomAction;
import application.Domain.Resource;

import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Jessica
 */
public interface CustomActionDB {
    
    public void connect() throws SQLException, ConnectionException;
    
    public void disconnect() throws SQLException;
    
    public void insertCustomAction(CustomAction ca) throws SQLException, ConnectionException;
    
	public void insertCAResource(List<Resource> resources, int customActionID) throws SQLException, ConnectionException;
	
    public void deleteCustomAction(int customActionID) throws SQLException, ConnectionException;
    
	public void deleteAllResourcesFromCA(int customActionID) throws SQLException, ConnectionException;
	
	public void deleteResourceFromCA(int customActionID, int resourceID) throws SQLException, ConnectionException;
	
	public void deleteAllCustomActionOfEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;
    
    public CustomAction findCustomActionByID(int idCustomAction) throws SQLException, ConnectionException, DataNotFoundException;
    
    public List<CustomAction> findCustomActionByEmployee(int idEmployee) throws SQLException, ConnectionException, DataNotFoundException;
    
}