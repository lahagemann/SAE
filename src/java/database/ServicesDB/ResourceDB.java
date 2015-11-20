/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDB;

import application.Domain.Resource;
import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface ResourceDB {
    public void connect() throws SQLException, ConnectionException;
    
    public void disconnect() throws SQLException;
    
    public void insertResource(Resource r) throws SQLException, ConnectionException;
    
    public void deleteResource(int resourceID) throws SQLException, ConnectionException;
    
    public void updateResource(Resource r) throws SQLException, ConnectionException, DataNotFoundException;
    
    public Resource findResourceByID(int ID) throws SQLException, ConnectionException, DataNotFoundException;
    
    public List<Resource> findResourceByRoom(int room) throws SQLException, ConnectionException, DataNotFoundException;
    
    public List<Resource> getAllResources() throws SQLException, ConnectionException, DataNotFoundException;
}
