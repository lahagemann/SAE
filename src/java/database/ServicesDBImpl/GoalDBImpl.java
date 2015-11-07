/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import application.Domain.Goal;
import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.GoalDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jessica
 */
public class GoalDBImpl implements GoalDB{

	private Connection connection = null;
	private Statement statement = null;
	
	public GoalDBImpl() {
		super();
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

	public void insertGoal(Goal g) throws SQLException, ConnectionException {

		String DateTime = DateConversion.DateConvert(g.getDay()) + " " + DateConversion.TimeConvert(g.getDay());

		String query = "INSERT INTO goal (value, day) VALUES " + "('" + g.getValue() + "', '" + DateTime + "');";

		connect();         
		statement.executeUpdate(query);

		disconnect();
	} 

	public void deleteGoal(int goalID) throws SQLException, ConnectionException {

		String query = "DELETE FROM goal WHERE identifier = " + goalID + ";";

		connect();        
		statement.executeUpdate(query);
		disconnect();
	}

	public List<Goal> getListGoal() throws SQLException, ConnectionException {

		ResultSet resultset = null;
		List<Goal> results = new ArrayList<Goal>();
		String query = "SELECT * FROM goal";

		connect();            
		resultset = statement.executeQuery(query);

		while (resultset.next()) {
			int identifier = resultset.getInt("identifier");
			float value = resultset.getFloat("value");

			Date day = new Date();
			Timestamp timestamp = resultset.getTimestamp("day");

			if (timestamp != null){
				day = new java.util.Date(timestamp.getTime());
			}

			Goal aux = new Goal(identifier,
					day,
					value);

			results.add(aux);
		}
		disconnect();
		return results;

	}

	public Goal findGoalByValue(float value) throws SQLException, ConnectionException {
		ResultSet resultset = null;
		String query = "SELECT * FROM goal WHERE value = " + value + ";";

		connect();
		resultset = statement.executeQuery(query);          

		int identifier = resultset.getInt("identifier");
		float avalue = resultset.getFloat("value");

		Date day = new Date();
		Timestamp timestamp = resultset.getTimestamp("day");

		if (timestamp != null){
			day = new java.util.Date(timestamp.getTime());

		}

		Goal goal = new Goal(identifier,
				day,
				avalue);
		disconnect();
		return goal;
	}

	@Override
	public Goal findGoal(int idGoal) throws SQLException, ConnectionException {
		ResultSet resultset = null;
		String query = "SELECT * FROM goal WHERE identifier = " + idGoal + ";";
		connect();
		resultset = statement.executeQuery(query);

		resultset.next();
		int identifier = resultset.getInt("identifier");
		float value = resultset.getFloat("value");

		Date day = new Date();
		Timestamp timestamp = resultset.getTimestamp("day");

		if (timestamp != null){
			day = new java.util.Date(timestamp.getTime());
		}

		Goal goal = new Goal(identifier,
				day,
				value);

		disconnect();
		return goal;
	}
}

