/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Impl;

import application.Domain.*;
import application.Interface.EmployeeService;
import application.Interface.InvalidUserException;
import application.Interface.LicenceException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.Connection.ConnectionException;
import database.ServicesDB.CustomActionDB;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.EmployeeDB;
import database.ServicesDB.InconsistentDBException;
import database.ServicesDB.ReportOnOffDB;
import database.ServicesDB.ResourceDB;
import database.ServicesDB.RoomDB;
import database.ServicesDBImpl.CustomActionDBImpl;
import database.ServicesDBImpl.EmployeeDBImpl;
import database.ServicesDBImpl.ReportOnOffDBImpl;
import database.ServicesDBImpl.ResourceDBImpl;
import database.ServicesDBImpl.RoomDBImpl;

/**
 *
 * @author Luiza
 */

public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl() {
        super();
    }

    @Override
    public void turnOnResource(int resourceID, int employeeID) throws LicenceException, SQLException, ConnectionException, DataNotFoundException, InconsistentDBException {
        ResourceDB resourceDB = new ResourceDBImpl();
        EmployeeDB employeeDB = new EmployeeDBImpl();
        Resource resource = resourceDB.findResourceByID(resourceID);
        Employee employee = employeeDB.findEmployeeByID(employeeID);

        if (checkLicense(resource, employee)) {
            createTurnOnReport(resource);
            resourceDB.updateResource(resource);
        } else {
            throw new LicenceException("Usuário não tem permissão para acessar esse recurso.");
        }
    }

    @Override
    public void turnOffResource(int resourceID, int employeeID) throws LicenceException, SQLException, ConnectionException, DataNotFoundException, InconsistentDBException {

        ResourceDB resourceDB = new ResourceDBImpl();
        EmployeeDB employeeDB = new EmployeeDBImpl();

        Resource resource = resourceDB.findResourceByID(resourceID);
        Employee employee = employeeDB.findEmployeeByID(employeeID);

        if (checkLicense(resource, employee)) {
            createTurnOffReport(resource);
            resourceDB.updateResource(resource);
        } else {
            throw new LicenceException("Usuário não tem permissão para acessar esse recurso.");
        }
    }

    @Override
    public void warnFlawResource(int resourceID, int employeeID) throws SQLException, ConnectionException, LicenceException, DataNotFoundException {

        ResourceDB resourceDB = new ResourceDBImpl();
        EmployeeDB employeeDB = new EmployeeDBImpl();

        Resource resource = resourceDB.findResourceByID(resourceID);
        Employee employee = employeeDB.findEmployeeByID(employeeID);

        if (checkLicense(resource, employee)) {
            createFlawReport(resource);
            resourceDB.updateResource(resource);
        } else {
            throw new LicenceException("Usuário não tem permissão para acessar esse recurso.");
        }

        return;
    }

    @Override
    public List<Resource> listResorcesWorkRoom(int employeeID) throws SQLException, ConnectionException, DataNotFoundException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        Employee e = employeeDB.findEmployeeByID(employeeID);

        ResourceDB resourceDB = new ResourceDBImpl();
        return resourceDB.findResourceByRoom(e.getWorkRoomID());
    }

    @Override
    public List<CustomAction> listCustomActions(int employeeID) throws SQLException, ConnectionException, DataNotFoundException {
        EmployeeDB employeeDB = new EmployeeDBImpl();

        Employee employee = employeeDB.findEmployeeByID(employeeID);

        return employee.getCustomActionList();
    }

    @Override
    public void customActionTurnOn(int caID, int employeeID) throws SQLException, ConnectionException, DataNotFoundException, LicenceException, InconsistentDBException {
        CustomActionDB customActionDB = new CustomActionDBImpl();
        ResourceDB resourceDB = new ResourceDBImpl();
        EmployeeDB employeeDB = new EmployeeDBImpl();

        Employee employee = employeeDB.findEmployeeByID(employeeID);

        CustomAction action = customActionDB.findCustomActionByID(caID);
        List<Resource> resources = action.getResourceList();

        for (Resource resource : resources) {
            if (checkLicense(resource, employee)) {
                createTurnOnReport(resource);
                resourceDB.updateResource(resource);
            } else {
                throw new LicenceException("Usuário não tem permissão para acessar esse recurso.");
            }

        }
    }

    @Override
    public void customActionTurnOff(int caID, int employeeID) throws SQLException, ConnectionException, DataNotFoundException, InconsistentDBException, LicenceException {
        CustomActionDB customActionDB = new CustomActionDBImpl();
        ResourceDB resourceDB = new ResourceDBImpl();
        EmployeeDB employeeDB = new EmployeeDBImpl();

        Employee employee = employeeDB.findEmployeeByID(employeeID);

        CustomAction action = customActionDB.findCustomActionByID(caID);
        List<Resource> resources = action.getResourceList();

        for (Resource resource : resources) {
            if (checkLicense(resource, employee)) {
                createTurnOffReport(resource);
                resourceDB.updateResource(resource);
            } else {
                throw new LicenceException("Usuário não tem permissão para acessar esse recurso.");
            }
        }

    }

    protected static boolean checkLicense(Resource resource, Employee employee) { // retorna true se é um admin ou se workroom é igual a location
        if (employee instanceof Admin) {
            return true;
        }
        if (employee.getWorkRoomID() == resource.getLocationID()) {
            return true;
        }
        return false;
    }

    private static void createTurnOnReport(Resource resource) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException { // Liga o recurso e cria o relatorio
        resource.turnOn();
        ReportOnOffDB reportDB = new ReportOnOffDBImpl();
        RoomDB roomDB = new RoomDBImpl();
        Room room = roomDB.findRoomByID( resource.getLocationID() );
        reportDB.insertReportOn(resource.getIdentifier(), room.getName(), new Date());
        return;
    }

    private static void createTurnOffReport(Resource resource) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException {  // Desliga, gasta os créditos da sala e cria o relatorio
        RoomDB roomDB = new RoomDBImpl();
        ReportOnOffDB reportDB = new ReportOnOffDBImpl();
        Room resourceRoom = roomDB.findRoomByID(resource.getLocationID());
        float consuption = resource.turnOff();
        resourceRoom.expendCredits(consuption);
        roomDB.updateRoom(resourceRoom);
        reportDB.insertReportOff(resource.getIdentifier(), new Date());
        return;
    }

    private static void createFlawReport(Resource resource) throws SQLException, ConnectionException {
        resource.warnFlaw();
		//ReportStatusDB reportDB = new ReportStatusDBImpl();
        //reportDB.insertReportBroken(resource.getIdentifier(), new Date());
        return;
    }

    @Override
    public Employee login(String email, String password) throws InvalidUserException, ConnectionException, DataNotFoundException, SQLException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        Employee employee;
        
        employee = employeeDB.findEmployeeByEmail(email);
        
        if (!employee.getPassword().equals(password)) {
            throw new InvalidUserException("Senha inválida");
        } else {
            return employee;
        }
    }

    @Override
    public boolean createCustomAction(int employeeID, String name, List<Resource> listResources) throws SQLException, ConnectionException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        CustomActionDB customActionDB = new CustomActionDBImpl();
        CustomAction customAction = new CustomAction(employeeID, name, listResources);
        customActionDB.insertCustomAction(customAction);
        return true;
    }

    @Override
    public Resource findResource(int id) throws SQLException, ConnectionException, DataNotFoundException {
        ResourceDB resourceDB = new ResourceDBImpl();
        return resourceDB.findResourceByID(id);
    }

    @Override
    public Room findRoom(int id) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException {
        RoomDB roomDB = new RoomDBImpl();
        return roomDB.findRoomByID(id);
    }

    @Override
    public Employee findEmployee(int id) throws SQLException, ConnectionException, DataNotFoundException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        return employeeDB.findEmployeeByID(id);
    }
}
