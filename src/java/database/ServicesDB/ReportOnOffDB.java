/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDB;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import application.Domain.TurnOnOrOffReport;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface ReportOnOffDB {
    public void connect() throws SQLException, ConnectionException;
    
    public void disconnect() throws SQLException;
    
    public boolean insertReportOn(int idResource, String name, Date date) throws SQLException, ConnectionException;
    
    public boolean insertReportOff(int idResource, Date date) throws SQLException, ConnectionException;
    
    public boolean resourceAlreadyOn (int idResource) throws SQLException, ConnectionException;
    
    public boolean resourceAlreadyOff (int idResource) throws SQLException, ConnectionException;    
    
	public List<TurnOnOrOffReport> findAllReportByDate(Date dayRequested) throws SQLException, ConnectionException;
	
}
