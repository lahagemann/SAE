/**
 * 
 */
package database.ServicesDBImpl;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import application.Domain.CustomAction;
import application.Domain.Resource;
import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.CustomActionDB;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.ResourceDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jessica
 */
public class CustomActionDBImpl implements CustomActionDB{

	private Connection connection = null;
	private Statement statement = null;


	public CustomActionDBImpl(){
		super();
	}

	public void connect() throws SQLException, ConnectionException {
		connection = ConnectionFactory.getConnection();
		statement = connection.createStatement();
	}

	public void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}

	public void insertCustomAction(CustomAction ca) throws SQLException, ConnectionException {

		String query = "INSERT INTO customaction (name, idEmployee) VALUES " 
				+ "('"+ ca.getName() + "', " + ca.getIdEmployee() + ");";

		connect();
		PreparedStatement pstmt = null;
		pstmt = (PreparedStatement) connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		rs.next();
		insertCAResource(ca.getResourceList() ,rs.getInt(1));
		disconnect();
	}

	public void insertCAResource(List<Resource> resources, int customActionID) throws SQLException, ConnectionException {        

		for (int i = 0; i < resources.size(); i++) { // uma query para cada resource a ser adicionado na acao personalizada

			String query = "INSERT INTO caresource (idCustomAction, idResource) VALUES " 
					+ "(" + customActionID + ", " + resources.get(i).getIdentifier() + ");";

			connect();
			statement.executeUpdate(query);
			disconnect();
		}
	}

	public void deleteCustomAction(int customActionID) throws SQLException, ConnectionException {

		deleteAllResourcesFromCA(customActionID);

		String query = "DELETE FROM customaction WHERE identifier = " + customActionID + ";";

		connect();
		statement.executeUpdate(query);

		disconnect();
	}

	public void deleteAllCustomActionOfEmployee(int employeeID) throws SQLException, ConnectionException, DataNotFoundException {

		List<CustomAction> listCA = findCustomActionByEmployee(employeeID);
		String query;

		connect();

		for(int i = 0; i < listCA.size(); i++){
			query = "DELETE FROM caresource WHERE idCustomAction = " + listCA.get(i).getIdentifier() + ";";
			statement.executeUpdate(query);
			query = "DELETE FROM customaction WHERE identifier = " + listCA.get(i).getIdentifier() + ";";
			statement.executeUpdate(query);
		}

		disconnect();
	}

	public void deleteAllResourcesFromCA(int customActionID) throws SQLException, ConnectionException {

		String query = "DELETE FROM caresource WHERE idCustomAction = " + customActionID + ";";

		connect();

		statement.executeUpdate(query);

		disconnect();           
	}

	public void deleteResourceFromCA(int customActionID, int resourceID) throws SQLException, ConnectionException {

		String query = "DELETE FROM caresource WHERE idCustomAction = " + customActionID + " and idResource = " + resourceID + " ;";

		connect();

		statement.executeUpdate(query);

		disconnect();           
	}

	public CustomAction findCustomActionByID(int idCustomAction) throws SQLException, ConnectionException, DataNotFoundException {

		ResultSet resultset = null;
		String query = "SELECT * FROM customaction WHERE identifier = " + idCustomAction + ";";
		String query2 = "SELECT * FROM caresource WHERE idCustomAction = " + idCustomAction + ";";

		connect();
		int identifier, idEmployee;
		String name;
		
		try{
			resultset = statement.executeQuery(query);
			resultset.next();
			identifier = resultset.getInt("identifier");
			name = resultset.getString("name");
			idEmployee = resultset.getInt("idEmployee");
		}
		catch(SQLException exc){
			disconnect();
			throw new DataNotFoundException("A Ação Personalizada não foi encontrada.");
		}

		ResourceDB rDB = new ResourceDBImpl();

		List<Resource> resourceList = new ArrayList<Resource>();

		try{
			resultset = statement.executeQuery(query2);
			while (resultset.next()) {
				resourceList.add(rDB.findResourceByID(resultset.getInt("idResource")));
			}
		} catch(SQLException exp){ } //não faz nada pois pode haver uma ação personalizada sem recursos

		CustomAction aux = new CustomAction(identifier, 
				name, 
				idEmployee,
				resourceList);

		disconnect();
		return aux;

	}

	public List<CustomAction> findCustomActionByEmployee(int idEmployee) throws SQLException, ConnectionException, DataNotFoundException {

		ResultSet resultset = null;
		List<CustomAction> results = new ArrayList<CustomAction>();
		String query = "SELECT * FROM customaction WHERE idEmployee = " + idEmployee + ";";

		connect();
		try{
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				results.add( this.findCustomActionByID( resultset.getInt("identifier")) );            
			}
		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("O funcionário não possui ações personalizadas.");
		}

		disconnect();
		return results;
	}
}