/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import application.Domain.Admin;
import application.Domain.CustomAction;
import application.Domain.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.CustomActionDB;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.EmployeeDB;

/**
 *
 * @author Jessica
 */
public class EmployeeDBImpl implements EmployeeDB {

	private Connection connection = null;
	private Statement statement = null;

	public EmployeeDBImpl() {
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

	@Override
	public void insertEmployee(Employee e) throws SQLException, ConnectionException {
		int isAdmin = 0;
		if(e instanceof Admin){
			isAdmin = 1;
		}
		String query = "INSERT INTO employee (name, cpf, email, password, idRoom, isAdmin) VALUES "
				+ "('" + e.getName() + "', '" + e.getCpf() + "', '" + e.getEmail() 
				+ "', '" + e.getPassword() + "', " + e.getWorkRoomID() + " , " + isAdmin + ");";

		connect();
		statement.executeUpdate(query);
		CustomActionDB caDB = new CustomActionDBImpl();
		List<CustomAction> listCustomAction = e.getCustomActionList();

		for(int i = 0; i < listCustomAction.size(); i++){
			caDB.insertCustomAction( listCustomAction.get(i) );
		}

		disconnect();
	}

	@Override
	public void deleteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException {
		String query = "DELETE FROM employee WHERE identifier = " + employeeID + ";";
		CustomActionDB caDB = new CustomActionDBImpl();
		connect();
		statement.executeUpdate(query);

		caDB.deleteAllCustomActionOfEmployee(employeeID);

		disconnect();

	}

	@Override
	public void updateEmployee(Employee e) throws SQLException, ConnectionException, DataNotFoundException {
		int isAdmin = 0;
		if(e instanceof Admin){
			isAdmin = 1;
		}
		
		if(e.getWorkRoomID() != this.findEmployeeByID( e.getIdentifier() ).getWorkRoomID() ){
			CustomActionDB caDB = new CustomActionDBImpl();
			caDB.deleteAllCustomActionOfEmployee( e.getIdentifier() );
		}

		String query = "UPDATE employee SET name = '" + e.getName() + "', cpf = '" + e.getCpf() + "', email = '" + e.getEmail()
				+ "', password = '" + e.getPassword() +  "', idRoom = " + e.getWorkRoomID() + ", isAdmin = " + isAdmin 
				+ " WHERE identifier = " + e.getIdentifier() + ";";

		connect();
		statement.executeUpdate(query);

		disconnect();

	}

	@Override
	public List<Employee> getEmployeeList() throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		List<Employee> results = new ArrayList<Employee>();
		String query = "SELECT * FROM employee";

		connect();

		try{ 
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				Employee aux;
				if(resultset.getInt("isAdmin") == 0){
					aux = new Employee(resultset.getString("name"),
							resultset.getInt("identifier"), 
							resultset.getString("cpf"),
							resultset.getString("email"),
							resultset.getString("password"),
							resultset.getInt("idRoom"),
							listOfCustomActions( resultset.getInt("identifier") ),
							resultset.getInt("isAdmin"));
				}
				else{
					aux = new Admin(resultset.getString("name"),
							resultset.getInt("identifier"), 
							resultset.getString("cpf"),
							resultset.getString("email"),
							resultset.getString("password"),
							resultset.getInt("idRoom"),
							listOfCustomActions( resultset.getInt("identifier") ),
							resultset.getInt("isAdmin"));
				}

				results.add(aux);

			}
		}
		catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("Não há funcionários cadastrados.");
		}
		
		disconnect();
		return results;
	}

	@Override
	public Employee findEmployeeByID(int ID) throws SQLException, ConnectionException, DataNotFoundException {

		ResultSet resultset = null;
		String query = "SELECT * FROM employee WHERE identifier = " + ID + ";";
		System.out.println(ID);
		Employee aux;
		connect();

		int identifier, room, isAdmin;
		String name, cpf, email, password;

		try{
			resultset = statement.executeQuery(query);
			resultset.next();
			name = resultset.getString("name");
			identifier = resultset.getInt("identifier"); 
			cpf = resultset.getString("cpf");
			email = resultset.getString("email");
			password = resultset.getString("password");
			room = resultset.getInt("idRoom");
			isAdmin = resultset.getInt("isAdmin");
		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("O funcionário não foi encontrado.");
		}

		if(isAdmin == 0){
			aux = new Employee(name, identifier, cpf, email, password, room, listOfCustomActions(identifier), isAdmin);
		}
		else{
			aux = new Admin(name, identifier, cpf, email, password, room, listOfCustomActions(identifier), isAdmin);
		}

		disconnect();
		return aux;
	}

	@Override
	public List<Employee> findEmployeeByRoom(int idroom) throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		List<Employee> results = new ArrayList<Employee>();
		String query = "SELECT * FROM employee WHERE idRoom = " + idroom + ";";

		connect();

		try{
			resultset = statement.executeQuery(query);                
			
			if(!resultset.next()) throw new DataNotFoundException("A sala não possui funcionários.");
			
			do {
				Employee aux;

				String name = resultset.getString("name");
				int identifier = resultset.getInt("identifier"); 
				String cpf = resultset.getString("cpf");
				String email = resultset.getString("email");
				String password = resultset.getString("password");
				int room = resultset.getInt("idRoom");
				int isAdmin = resultset.getInt("isAdmin");
				if(isAdmin == 0){
					aux = new Employee(name, identifier, cpf, email, password, room, listOfCustomActions(identifier), isAdmin);
				}
				else{
					aux = new Admin(name, identifier, cpf, email, password, room, listOfCustomActions(identifier), isAdmin);
				}
				results.add(aux);
			} while (resultset.next());
		} catch(SQLException exp){
			disconnect();
		}

		disconnect();
		return results;
	}

	@Override
	public Employee findEmployeeByEmail(String email) throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		Employee employee;
		String query = "SELECT * FROM employee WHERE email = '" + email + "';";
		int identifier, room, isAdmin;
		String name, cpf, password;
		connect();
		try{
			resultset = statement.executeQuery(query);
			resultset.next();
			name = resultset.getString("name");
			identifier = resultset.getInt("identifier"); 
			cpf = resultset.getString("cpf");
			password = resultset.getString("password");
			room = resultset.getInt("idRoom");
			isAdmin = resultset.getInt("isAdmin");
		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("O funcionário não foi encontrado.");
		}

		if(isAdmin == 0){
			employee = new Employee(name, identifier, cpf, email, password, room, listOfCustomActions(identifier), isAdmin);
		} else {
			employee = new Admin(name, identifier, cpf, email, password, room, listOfCustomActions(identifier), isAdmin);
		}
		disconnect();
		return employee;
	}

	public boolean promoteEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException{

		ResultSet resultset = null;
		String query = "SELECT * FROM employee WHERE identifier = " + employeeID + ";";
		connect();
		resultset = statement.executeQuery(query);
		resultset.next();

		try{
			if(resultset.getInt("isAdmin") == 0){
				query = "UPDATE employee SET isAdmin = " + 1 + " WHERE identifier = " + employeeID + ";";
				statement.executeUpdate(query);
				disconnect();
				return true;
			}
			else{
				disconnect();
				return false;
			}
		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("O funcionário não foi encontrado.");
		}
	}


private static List<CustomAction> listOfCustomActions(int employeeID){
	CustomActionDB caDB = new CustomActionDBImpl();
	List<CustomAction> customActionList = new ArrayList<CustomAction>();

	try{
		customActionList = caDB.findCustomActionByEmployee(employeeID);
	}
	catch(Exception e){ } // empregado pode não ter ações personalizadas
	return customActionList;
}

}


