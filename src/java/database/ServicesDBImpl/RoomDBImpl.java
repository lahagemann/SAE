/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import application.Domain.Employee;
import application.Domain.Goal;
import application.Domain.Resource;
import application.Domain.Room;
import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.EmployeeDB;
import database.ServicesDB.GoalDB;
import database.ServicesDB.ResourceDB;
import database.ServicesDB.RoomDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jessica
 */
public class RoomDBImpl implements RoomDB {

	private Connection connection = null;
	private Statement statement = null;

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

	@Override
	public void insertRoom(Room r) throws SQLException, ConnectionException {

		// query para inserir novo registro na tabela room
		// informaçoes de relacionamento do resource e employee eh feita em outra query
		String query = "INSERT INTO room (number, creditAmount, idGoal) VALUES " 
				+ "('" + r.getRoomNumber() + "', '" + r.getCreditAmount() + "', '" 
				+ r.getDailyGoal().getIdentifier() + "');";

		connect();
		statement.executeUpdate(query);

		disconnect();
	}

	@Override
	public void deleteRoom(int roomID) throws SQLException, ConnectionException {

		String query = "DELETE FROM room WHERE identifier = " + roomID + ";";

		connect();
		statement.executeUpdate(query);

		String query2 = "UPDATE employee SET idRoom = 0 WHERE idRoom = " + roomID + ";";

		statement.executeUpdate(query2);

		String query3 = "UPDATE resource SET idRoom = 0 WHERE idRoom = " + roomID + ";";
		
		statement.executeUpdate(query3);

		disconnect();

	}

	@Override
	public void updateRoom(Room r) throws SQLException, ConnectionException {

		String query = "UPDATE room SET number = '" + r.getRoomNumber() 
				+ "', creditAmount = '" + r.getCreditAmount() + "', idGoal = '" + r.getDailyGoal().getIdentifier()
				+ "' WHERE identifier = " + r.getIdentifier() + ";";

		connect();
		statement.executeUpdate(query);
		disconnect();
	}

	@Override
	public List<Room> getRoomList() throws SQLException, ConnectionException {

		ResultSet resultset = null;
		List<Room> results = new ArrayList<Room>();
		String query = "SELECT * FROM room";

		connect();
		resultset = statement.executeQuery(query);

		while (resultset.next()) {
			int identifier = resultset.getInt("identifier");
			int number = resultset.getInt("number");
			float credit = resultset.getFloat("creditAmount");
			int idGoal = resultset.getInt("idGoal");

			GoalDB gDB = new GoalDBImpl();
			Goal g = gDB.findGoal(idGoal);

			ResourceDB rDB = new ResourceDBImpl();
			List<Resource> resourceList = rDB.findResourceByRoom(resultset.getInt("identifier"));

			EmployeeDB eDB = new EmployeeDBImpl();
			List<Employee> employeeList = eDB.findEmployeeByRoom(resultset.getInt("identifier"));

			Room aux = new Room(identifier,
					number,
					credit,
					g, 
					resourceList, 
					employeeList);
			results.add(aux);
		}
		disconnect();
		return results;

	}

	@Override
	public Room findRoomByID(int id) throws SQLException, ConnectionException {

		ResultSet resultset = null;
		String query = "SELECT * FROM room WHERE identifier = " + id + ";";

		connect(); 

		resultset = statement.executeQuery(query);
		resultset.next();

		int identifier = resultset.getInt("identifier");
		int number = resultset.getInt("number");
		float credit = resultset.getFloat("creditAmount");
		int idGoal = resultset.getInt("idGoal");
                
                System.out.println(identifier + " " +number + " " + credit + " " + idGoal);

		GoalDB gDB = new GoalDBImpl();
		Goal g = gDB.findGoal(idGoal);

		ResourceDB rDB = new ResourceDBImpl();
		List<Resource> resourceList = rDB.findResourceByRoom(identifier);

		EmployeeDB eDB = new EmployeeDBImpl();
		List<Employee> employeeList = eDB.findEmployeeByRoom(identifier);

		Room aux = new Room(identifier,
				number,
				credit,
				g, 
				resourceList, 
				employeeList);       
		disconnect();
		return aux;


	}
}