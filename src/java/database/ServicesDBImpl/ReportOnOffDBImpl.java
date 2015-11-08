/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.ReportOnOffDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
/**
 *
 * @author Jessica
 */
public class ReportOnOffDBImpl implements ReportOnOffDB {
    
    private Connection connection = null;
    private Statement statement = null;
    
    public ReportOnOffDBImpl() {
		super();
	}

	@Override
	public void connect() throws SQLException, ConnectionException {
		connection = ConnectionFactory.getConnection();
		statement = connection.createStatement();
	}

	@Override
	public void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}
       
    public boolean insertReportOn(int idResource, Date date) throws SQLException, ConnectionException {
        
        boolean isOn = resourceAlreadyOn(idResource);
        
        if (!isOn) {
            String DateTime = DateConversion.DateConvert(date) + " " + DateConversion.TimeConvert(date);        
            String query = "INSERT INTO reportonoff (idResource, initialTime) VALUES " + "('" + idResource + "', '" + DateTime + "');";

            	connect();
            	statement.executeUpdate(query);
                   
                disconnect();
                return true;
        }
        return false;
    }
    
    public boolean insertReportOff(int idResource, Date date) throws SQLException, ConnectionException {
        
        boolean isOff = resourceAlreadyOff(idResource);
        
        if (!isOff) {
            
            String DateTime = DateConversion.DateConvert(date) + " " + DateConversion.TimeConvert(date);

            String query = "UPDATE reportonoff SET finalTime = '" + DateTime 
                    + "' WHERE idResource = " + idResource + " AND initialTime is not NULL AND finalTime is NULL;";
               
                connect();         
                statement.executeUpdate(query);

                disconnect();
                
            return true;
        }
        return false;
    }
    
    public boolean resourceAlreadyOn (int idResource) throws SQLException, ConnectionException {
        
        ResultSet resultset = null;        
        String query = "SELECT * FROM reportonoff WHERE idResource = " + idResource + " AND initialTime is not NULL AND finalTime is NULL;";
        
            connect();
            resultset = statement.executeQuery(query);
            
            if(!resultset.next())
                return false;
            else
                return true;  
            
             
    }
    
    public boolean resourceAlreadyOff (int idResource) throws SQLException, ConnectionException {
        
        ResultSet resultset = null;        
        String query = "SELECT * FROM reportonoff WHERE idResource = " + idResource + " AND initialTime is not NULL AND finalTime is NULL;";
        
            connect();
            resultset = statement.executeQuery(query);
            
            if(!resultset.next())
                return true;
            else
                return false;  
            
        }
    
}
