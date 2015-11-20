/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Impl;

import application.Domain.*;
import application.Interface.AdminService;
import application.Interface.LicenceException;

import database.Connection.ConnectionException;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.EmployeeDB;
import database.ServicesDB.GoalDB;
import database.ServicesDB.InconsistentDBException;
import database.ServicesDB.InvalidGoalException;
import database.ServicesDB.ReportOnOffDB;
import database.ServicesDB.ResourceDB;
import database.ServicesDB.RoomDB;
import database.ServicesDBImpl.EmployeeDBImpl;
import database.ServicesDBImpl.GoalDBImpl;
import database.ServicesDBImpl.ReportOnOffDBImpl;
import database.ServicesDBImpl.ResourceDBImpl;
import database.ServicesDBImpl.RoomDBImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiza
 */
public class AdminServiceImpl extends EmployeeServiceImpl implements AdminService {

    @Override
    public void warnRepairResource(int resourceID, int employeeID) throws SQLException, ConnectionException, LicenceException, DataNotFoundException {

        ResourceDB resourceDB = new ResourceDBImpl();

        Resource resource = resourceDB.findResourceByID(resourceID);

        createRepairReport(resource);
        resourceDB.updateResource(resource);

        return;
    }

    @Override
    public List<Room> listAllRooms() throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException {
        RoomDB roomDB = new RoomDBImpl();
        return roomDB.getRoomList();
    }

    @Override
    public List<Employee> listAllEmployees() throws SQLException, ConnectionException, DataNotFoundException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        return employeeDB.getEmployeeList();

    }

    @Override
    public List<Resource> listAllResources() throws SQLException, ConnectionException, DataNotFoundException {
        ResourceDB resourceDB = new ResourceDBImpl();
        return resourceDB.getAllResources();
    }

    @Override
    public boolean createResource(String name, String type, float consumption, int roomID) throws SQLException, ConnectionException {
        ResourceDB resourceDB = new ResourceDBImpl();
        Resource resource = new Resource(name, type, consumption, roomID); // retorna sala que tem ID roomID
        resourceDB.insertResource(resource);
        return true;
    }

    @Override
    public boolean createEmployee(String name, String cpf, String email,
            String password, int roomID, int isAdmin) throws SQLException, ConnectionException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        Employee employee = new Employee(name, cpf, email, password, roomID, isAdmin);
        employeeDB.insertEmployee(employee);
        return true;
    }

    @Override
    public boolean createRoom(String roomName, float creditAmount, int dailyGoalID) throws SQLException, ConnectionException, DataNotFoundException {
        RoomDB roomDB = new RoomDBImpl();
        GoalDB goalDB = new GoalDBImpl();
        Room room = new Room(roomName, creditAmount, goalDB.findLastGoal());
        roomDB.insertRoom(room);
        return true;
    }

    @Override
    public boolean createGoal(Date day, float value) throws SQLException, ConnectionException, InvalidGoalException {
        GoalDB goalDB = new GoalDBImpl();
        Goal goal = new Goal(day, value);
        goalDB.insertGoal(goal);
        return true;
    }

    @Override
    public boolean deleteResource(int resourceID) throws SQLException, ConnectionException {
        ResourceDB resourceDB = new ResourceDBImpl();
        resourceDB.deleteResource(resourceID);
        return true;
    }

    @Override
    public boolean deleteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        employeeDB.deleteEmployee(employeeID);
        return true;
    }

    @Override
    public boolean deleteRoom(int roomID) throws SQLException, ConnectionException {
        RoomDB roomDB = new RoomDBImpl();
        roomDB.deleteRoom(roomID);
        return true;
    }

    @Override
    public boolean deleteGoal(int goalID) throws SQLException, ConnectionException {
        GoalDB goalDB = new GoalDBImpl();
        goalDB.deleteGoal(goalID);
        return true;
    }

    @Override
    public boolean promoteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException {
        boolean result;
        EmployeeDB employeeDB = new EmployeeDBImpl();
        result = employeeDB.promoteEmployee(employeeID);
        return result;
    }

    @Override
    public void updateEmployee(Employee e) throws SQLException, ConnectionException, DataNotFoundException {
        EmployeeDB employeeDB = new EmployeeDBImpl();
        employeeDB.updateEmployee(e);
    }

    @Override
    public void updateResource(Resource r) throws SQLException, ConnectionException, DataNotFoundException {
        ResourceDB resourceDB = new ResourceDBImpl();
        resourceDB.updateResource(r);
    }

    @Override
    public void updateRoom(Room r) throws SQLException, ConnectionException {
        RoomDB roomDB = new RoomDBImpl();
        roomDB.updateRoom(r);
    }

    private static void createRepairReport(Resource resource) throws SQLException, ConnectionException {
        resource.warnRepair();
		//ReportStatusDB reportDB = new ReportStatusDBImpl();
        //reportDB.insertReportRepair(resource.getIdentifier(), new Date());

		// mandar mensagens para os funcionarios da sala que esta o recurso
        return;
    }

	@Override
	public List<TurnOnOrOffReport> reportsOfDay(String day) throws SQLException, ConnectionException {
		ReportOnOffDB reportOnOffDB = new ReportOnOffDBImpl();
		return reportOnOffDB.findAllReportByDate( convertStringToDate(day) );
	}

	private Date convertStringToDate(String day){
		String delims = "[/]";
		String[] tokens = day.split(delims);
		return new Date(Integer.parseInt(tokens[2]) - 1900, Integer.parseInt(tokens[1]) - 1, Integer.parseInt(tokens[0]));
	}
	
}
