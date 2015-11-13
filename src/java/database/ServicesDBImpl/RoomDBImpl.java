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
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.EmployeeDB;
import database.ServicesDB.GoalDB;
import database.ServicesDB.InconsistentDBException;
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
		String query = "INSERT INTO room (number, creditAmount) VALUES " 
				+ "('" + r.getRoomNumber() + "', '" + r.getCreditAmount() + "');";

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
	public List<Room> getRoomList() throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException {

		ResultSet resultset = null;
		List<Room> results = new ArrayList<Room>();
		String query = "SELECT * FROM room";
		connect();

		int identifier = 0, number = 0, idGoal = 0;
		float credit = 0;

		try{ 
			resultset = statement.executeQuery(query);


			while (resultset.next()) {
				identifier = resultset.getInt("identifier");
				number = resultset.getInt("number");
				credit = resultset.getFloat("creditAmount");
				idGoal = resultset.getInt("idGoal");
			}
		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("A sala não foi encontrada.");
		}
		ResourceDB rDB = new ResourceDBImpl();
		EmployeeDB eDB = new EmployeeDBImpl();
		GoalDB gDB = new GoalDBImpl();
		List<Resource> resourceList;
		List<Employee> employeeList;
		Goal g;
		try{
			g = gDB.findGoalByID(idGoal);
			resourceList = rDB.findResourceByRoom(resultset.getInt("identifier"));
			employeeList = eDB.findEmployeeByRoom(resultset.getInt("identifier"));
		} catch(DataNotFoundException exp){
			throw new InconsistentDBException("O banco de dados está inconsistente, por favor contate o suporte técnico.");
		} finally{
			disconnect();
		}


		Room aux = new Room(identifier,
				number,
				credit,
				g, 
				resourceList, 
				employeeList);
		results.add(aux);


		return results;

	}

	@Override
	public Room findRoomByID(int id) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException {

		ResultSet resultset = null;
		String query = "SELECT * FROM room WHERE identifier = " + id + ";";
		int identifier = 0, number = 0, idGoal = 0;
		float credit = 0;
		connect(); 

		try{
			resultset = statement.executeQuery(query);
			resultset.next();

			identifier = resultset.getInt("identifier");
			number = resultset.getInt("number");
			credit = resultset.getFloat("creditAmount");
			idGoal = resultset.getInt("idGoal");

			System.out.println(identifier + " " +number + " " + credit + " " + idGoal);

		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("A sala não foi encontrada."); 
		}

		GoalDB gDB = new GoalDBImpl();
		Goal g;
		ResourceDB rDB = new ResourceDBImpl();
		EmployeeDB eDB = new EmployeeDBImpl();
		List<Resource> resourceList;
		List<Employee> employeeList;

		try{
			resourceList = rDB.findResourceByRoom(resultset.getInt("identifier"));
			employeeList = eDB.findEmployeeByRoom(resultset.getInt("identifier"));
			g = gDB.findGoalByID(idGoal);
		} catch(DataNotFoundException exp){
			throw new InconsistentDBException("O banco de dados está inconsistente, por favor contate o suporte técnico.");
		}
		finally{
			disconnect();
		}

		Room aux = new Room(identifier,
				number,
				credit,
				g, 
				resourceList, 
				employeeList);       
		return aux;


	}
}