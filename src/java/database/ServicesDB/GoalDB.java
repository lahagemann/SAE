/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDB;

import application.Domain.Goal;
import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface GoalDB {
    public void connect() throws SQLException, ConnectionException;
    
    public void disconnect() throws SQLException;
    
    public void insertGoal(Goal g) throws SQLException, ConnectionException;
    
    public void deleteGoal(int goalID) throws SQLException, ConnectionException;
    
    public List<Goal> getListGoal() throws SQLException, ConnectionException;
    
    public Goal findGoal(int idGoal) throws SQLException, ConnectionException;
}
