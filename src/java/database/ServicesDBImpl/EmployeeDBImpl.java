/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import application.Domain.Admin;
import application.Domain.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
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

		// depois adicionar modificacoes para CustomAction

		connect();
		statement.executeUpdate(query);
		disconnect();

	}

	@Override
	public void deleteEmployee(int employeeID) throws SQLException, ConnectionException {
		String query = "DELETE FROM employee WHERE identifier = " + employeeID + ";";

		connect();
		statement.executeUpdate(query);

		disconnect();

	}

	@Override
	public void updateEmployee(Employee e) throws SQLException, ConnectionException {
		int isAdmin = 0;
		if(e instanceof Admin){
			isAdmin = 1;
		}

		String query = "UPDATE employee SET name = '" + e.getName() + "', cpf = '" + e.getCpf() + "', email = '" + e.getEmail()
				+ "', password = '" + e.getPassword() +  "', idRoom = " + e.getWorkRoomID() + ", isAdmin = " + isAdmin 
				+ " WHERE identifier = " + e.getIdentifier() + ";";

		connect();
		statement.executeUpdate(query);

		disconnect();

	}

	@Override
	public List<Employee> getEmployeeList() throws SQLException, ConnectionException {
		ResultSet resultset = null;
		List<Employee> results = new ArrayList<Employee>();
		String query = "SELECT * FROM employee";

		connect();
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
						resultset.getInt("isAdmin"));
			}
			else{
				aux = new Admin(resultset.getString("name"),
						resultset.getInt("identifier"), 
						resultset.getString("cpf"),
						resultset.getString("email"),
						resultset.getString("password"),
						resultset.getInt("idRoom"),
						resultset.getInt("isAdmin"));
			}
			results.add(aux);
		}
		disconnect();

		return results;
	}

	@Override
	public Employee findEmployeeByID(int ID) throws SQLException, ConnectionException {

		ResultSet resultset = null;
		String query = "SELECT * FROM employee WHERE identifier = " + ID + ";";
		Employee aux;
		connect();
		resultset = statement.executeQuery(query);
		resultset.next();
		if(resultset.getInt("isAdmin") == 0){
			aux = new Employee(resultset.getString("name"),
					resultset.getInt("identifier"), 
					resultset.getString("cpf"),
					resultset.getString("email"),
					resultset.getString("password"),
					resultset.getInt("idRoom"),
					resultset.getInt("isAdmin"));
		}
		else{
			aux = new Admin(resultset.getString("name"),
					resultset.getInt("identifier"), 
					resultset.getString("cpf"),
					resultset.getString("email"),
					resultset.getString("password"),
					resultset.getInt("idRoom"),
					resultset.getInt("isAdmin"));

		}
		disconnect();

		return aux;

	}

	@Override
	public List<Employee> findEmployeeByRoom(int idroom) throws SQLException, ConnectionException {
		ResultSet resultset = null;
		List<Employee> results = new ArrayList<Employee>();
		String query = "SELECT * FROM employee WHERE idRoom = " + idroom + ";";

		connect();
		resultset = statement.executeQuery(query);
		resultset.next();

		while (resultset.next()) {
			Employee aux;
			if(resultset.getInt("isAdmin") == 0){
				aux = new Employee(resultset.getString("name"),
						resultset.getInt("identifier"), 
						resultset.getString("cpf"),
						resultset.getString("email"),
						resultset.getString("password"),
						resultset.getInt("idRoom"),
						resultset.getInt("isAdmin"));
			}
			else{
				aux = new Admin(resultset.getString("name"),
						resultset.getInt("identifier"), 
						resultset.getString("cpf"),
						resultset.getString("email"),
						resultset.getString("password"),
						resultset.getInt("idRoom"),
						resultset.getInt("isAdmin"));
			}
			results.add(aux);
		}
		disconnect();

		return results;

	}

	@Override
	public Employee findEmployeeByEmail(String email) throws SQLException, ConnectionException {
		ResultSet resultset = null;
		Employee employee;
		String query = "SELECT * FROM employee WHERE email = '" + email + "';";

		connect();
		resultset = statement.executeQuery(query);
		resultset.next();
                
                String name = resultset.getString("name");
		int identifier = resultset.getInt("identifier"); 
		String cpf = resultset.getString("cpf");
		String mail = resultset.getString("email");
		String password = resultset.getString("password");
		int room = resultset.getInt("idRoom");
		int isAdmin = resultset.getInt("isAdmin");
                
                if(isAdmin == 0)
                    employee = new Employee(name, identifier, cpf, mail, password, room, isAdmin);
                else
                    employee = new Admin(name, identifier, cpf, mail, password, room, isAdmin);

//		if(resultset.getInt("isAdmin") == 0){
//			employee = new Employee(resultset.getString("name"),
//					resultset.getInt("identifier"), 
//					resultset.getString("cpf"),
//					resultset.getString("email"),
//					resultset.getString("password"),
//					resultset.getInt("idRoom"),
//					resultset.getInt("isAdmin"));
//		}
//		else{
//			employee = new Admin(resultset.getString("name"),
//					resultset.getInt("identifier"), 
//					resultset.getString("cpf"),
//					resultset.getString("email"),
//					resultset.getString("password"),
//					resultset.getInt("idRoom"),
//					resultset.getInt("isAdmin"));
//		}

		disconnect();

		return employee;
	}
	
	public boolean promoteEmployee(int employeeID) throws SQLException, ConnectionException{
		
		ResultSet resultset = null;
		String query = "SELECT * FROM employee WHERE identifier = " + employeeID + ";";
		connect();
		resultset = statement.executeQuery(query);
		resultset.next();
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
	}

}


