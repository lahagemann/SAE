/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Interface;

import application.Domain.CustomAction;
import application.Domain.Employee;
import application.Domain.Resource;
import application.Domain.Room;

import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.InconsistentDBException;

/**
 *
 * @author Luiza
 */
public interface EmployeeService {

    public void turnOnResource(int resourceID, int employeeID) throws LicenceException, SQLException, ConnectionException, DataNotFoundException, InconsistentDBException;

    public void turnOffResource(int resourceID, int employeeID) throws LicenceException, SQLException, ConnectionException, DataNotFoundException, InconsistentDBException;

    public void warnFlawResource(int resourceID, int employeeID) throws SQLException, ConnectionException, LicenceException, DataNotFoundException;

    public List<Resource> listResorcesWorkRoom(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;

    public List<CustomAction> listCustomActions(int employeeID) throws SQLException, ConnectionException, DataNotFoundException;

    void customActionTurnOn(int caID, int employeeID) throws SQLException, ConnectionException, DataNotFoundException, LicenceException, InconsistentDBException;

    void customActionTurnOff(int caID, int employeeID) throws SQLException, ConnectionException, DataNotFoundException, InconsistentDBException, LicenceException;

    public Employee login(String email, String password) throws InvalidUserException, ConnectionException, DataNotFoundException, SQLException;

    public boolean createCustomAction(int employeeID, String name, List<Resource> listResources) throws SQLException, ConnectionException;

    Employee findEmployee(int id) throws SQLException, ConnectionException, DataNotFoundException;

    Resource findResource(int id) throws SQLException, ConnectionException, DataNotFoundException;

    Room findRoom(int id) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException;
}
