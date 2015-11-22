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
	public void connect() throws ConnectionException, SQLException {
		connection = ConnectionFactory.getConnection();
		statement = connection.createStatement();
	}

	@Override
	public void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}
	
	@Override
	public boolean hasRoom(int idRoom) throws ConnectionException, SQLException{
		ResultSet resultset = null;
		String query = "SELECT * FROM room WHERE identifier = " + idRoom + ";";
		int identifier;
		connect(); 

		try{
			resultset = statement.executeQuery(query);
			resultset.next();
			identifier = resultset.getInt("identifier");
			System.out.println("aqquui " + identifier);

			return identifier == idRoom;
		} catch(SQLException exp){
			return false;
		}
	}

	@Override
	public void insertRoom(Room r) throws SQLException, ConnectionException {

		// query para inserir novo registro na tabela room
		// informaçoes de relacionamento do resource e employee eh feita em outra query
		String query = "INSERT INTO room (name, creditAmount, idGoal) VALUES " 
				+ "('" + r.getName() + "', '" + r.getCreditAmount() +  "', '" + r.getDailyGoal().getIdentifier() + "');";

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

		String query = "UPDATE room SET name = '" + r.getName() 
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

		int identifier = 0, idGoal = 0;
		float credit = 0;
		String name;

		resultset = statement.executeQuery(query);
		
		if(!resultset.next()) throw new DataNotFoundException("O sistema não tem salas registradas.");
		
		do {
			try{ 
				identifier = resultset.getInt("identifier");
				name = resultset.getString("name");
				credit = resultset.getFloat("creditAmount");
				idGoal = resultset.getInt("idGoal");
			} catch(SQLException exp){
				disconnect();
				throw new DataNotFoundException("A sala não foi encontrada.");
			}

			ResourceDB rDB = new ResourceDBImpl();
			EmployeeDB eDB = new EmployeeDBImpl();
			GoalDB gDB = new GoalDBImpl();
			List<Resource> resourceList = new ArrayList<Resource>();
			List<Employee> employeeList = new ArrayList<Employee>();
			Goal g = null;
			
			try{
				resourceList = rDB.findResourceByRoom(resultset.getInt("identifier"));
			} catch(DataNotFoundException e){ /*Sala pode não ter recursos*/ }
			
			try{
				employeeList = eDB.findEmployeeByRoom(resultset.getInt("identifier"));
			} catch(DataNotFoundException e){ /*Sala pode não ter funcionários*/ }
			
			try{
				g = gDB.findGoalByID(idGoal);
			} catch(DataNotFoundException e){ 
				throw new InconsistentDBException("Inconsistência no Banco de Dados, sala não tem meta. Entre em contato com o suporte.");
			}
			
			results.add( 
					new Room(identifier,
							 name,
							 credit,
							 g, 
							 resourceList, 
							 employeeList) 
					);
		} while (resultset.next());

		disconnect();
		return results;

	}

	@Override
	public Room findRoomByID(int id) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException {
		
		ResultSet resultset = null;
		String query = "SELECT * FROM room WHERE identifier = " + id + ";";
		int identifier = 0, idGoal = 0;
		float credit = 0;
		String name;
		connect(); 

		try{
			resultset = statement.executeQuery(query);
			resultset.next();

			identifier = resultset.getInt("identifier");
			name = resultset.getString("name");
			credit = resultset.getFloat("creditAmount");
			idGoal = resultset.getInt("idGoal");

		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("A sala não foi encontrada."); 
		}

		GoalDB gDB = new GoalDBImpl();
		Goal g = null;
		ResourceDB rDB = new ResourceDBImpl();
		EmployeeDB eDB = new EmployeeDBImpl();
		List<Resource> resourceList = null;
		List<Employee> employeeList = null;

		try{
			resourceList = rDB.findResourceByRoom(resultset.getInt("identifier"));
		} catch(DataNotFoundException e){ /*Sala pode não ter recursos*/ }
		try{
			employeeList = eDB.findEmployeeByRoom(resultset.getInt("identifier"));
		} catch(DataNotFoundException e){ /*Sala pode não ter funcionários*/ }
		try{
			g = gDB.findGoalByID(idGoal);
		} catch(DataNotFoundException e){ 
			throw new InconsistentDBException("Inconsistência no Banco de Dados, sala não tem meta. Entre em contato com o suporte.");
		}

		return new Room(identifier,
				name,
				credit,
				g, 
				resourceList, 
				employeeList); 
	}
}