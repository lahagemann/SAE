/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import application.Domain.Goal;
import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.GoalDB;
import database.ServicesDB.InvalidGoalException;

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

	public void insertGoal(Goal g) throws SQLException, ConnectionException, InvalidGoalException {

		String DateTime = DateConversion.DateConvert(g.getDay()) + " " + DateConversion.TimeConvert(g.getDay());

		String query = "INSERT INTO goal (value, day) VALUES " + "('" + g.getValue() + "', '" + DateTime + "');";

		if( isAfterToday( g.getDay() ) ){
			if(isAnotherMonth( g.getDay() ) ){
				if(isValidValue( g.getValue() ) ){
					connect();         
					statement.executeUpdate(query);
					disconnect();
				}
				else{
					throw new InvalidGoalException("O valor da meta é inválido.");
				}
			}
			else{
				throw new InvalidGoalException("A meta deste mês já foi definida.");
			}
		}
		else{
			throw new InvalidGoalException("A meta definida deve ser posterior a data vigente.");
		}
	} 

	public void deleteGoal(int goalID) throws SQLException, ConnectionException, DataNotFoundException, InvalidGoalException {

		String query = "DELETE FROM goal WHERE identifier = " + goalID + ";";
		
		Goal goal = this.findGoalByID(goalID);
		
		if( isAfterToday( goal.getDay() ) ){
			if(isAnotherMonth( goal.getDay() ) ){
				connect();        
				statement.executeUpdate(query);
				disconnect();
			}
			else{
				throw new InvalidGoalException("Essa meta não pode ser removida pois está em vigência.");
			}
		}
		else{
			throw new InvalidGoalException("Essa meta não pode ser removida pois esteve/está em vigência.");
		}
	}

	public List<Goal> getListGoal() throws SQLException, ConnectionException, DataNotFoundException {

		ResultSet resultset = null;
		List<Goal> results = new ArrayList<Goal>();
		String query = "SELECT * FROM goal";

		connect();          

		try{
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
		}catch(SQLException exp){
			throw new DataNotFoundException("Não há metas cadastradas.");
		} finally{ 
			disconnect();
		}
		return results;
	}

	@Override
	public Goal findGoalByID(int idGoal) throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		String query = "SELECT * FROM goal WHERE identifier = " + idGoal + ";";
		connect();
		int identifier;
		float value;
		Date day = new Date();

		try{

			resultset = statement.executeQuery(query);
			resultset.next();
			identifier = resultset.getInt("identifier");
			value = resultset.getFloat("value");

			Timestamp timestamp = resultset.getTimestamp("day");

			if (timestamp != null){
				day = new java.util.Date(timestamp.getTime());
			}

		} catch(SQLException exp){
			throw new DataNotFoundException("A meta não foi encontrada.");
		} finally{
			disconnect();
		}

		return new Goal(identifier,
				day,
				value);
	}

	@Override
	public Goal findLastGoal() throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		String query = "SELECT Max(day) FROM goal;";
		connect();
		int identifier;
		float value;
		Date day = new Date();
		try{
			
			resultset = statement.executeQuery(query);
			resultset.next();
			Date dateOfLastGoal = resultset.getTimestamp("Max(day)");
			query = "SELECT * FROM goal WHERE day = '" + DateConversion.DateConvert(dateOfLastGoal) + " " +  DateConversion.TimeConvert(dateOfLastGoal) + "';";
			
			resultset = statement.executeQuery(query);
			resultset.next();
			
			identifier = resultset.getInt("identifier");
			value = resultset.getFloat("value");

			Timestamp timestamp = resultset.getTimestamp("day");

			if (timestamp != null){
				day = new java.util.Date(timestamp.getTime());
			}

		} catch(SQLException exp){
			System.out.println(exp.getMessage());
			throw new DataNotFoundException("A meta não foi encontrada.");
		} finally{
			disconnect();
		}

		return new Goal(identifier,
				day,
				value);
	}
	
	private boolean isAfterToday(Date dateOfGoal){
		Date today = new Date();
		if( today.before( dateOfGoal ) ){
			return true;
		}
		return false;
	}

	private boolean isAnotherMonth(Date dateOfGoal) throws SQLException, ConnectionException{
		Date today = new Date();
		int i= 0 ;
		System.out.println((dateOfGoal.getMonth() + 1) + " / " + (dateOfGoal.getYear() + 1900));
		String query = "SELECT * FROM goal WHERE month(day) = " + ( dateOfGoal.getMonth() + 1) + " and year(day) = " + ( dateOfGoal.getYear()+ 1900 ) + ";";
		connect();
		ResultSet resultset = null;
		try{
			resultset = statement.executeQuery(query);	
		} catch(SQLException exp){
			if( today.getMonth() != dateOfGoal.getMonth() ){
				return true;
			}
		} finally{
			disconnect();
		}
		return false;
	}	

	private boolean isValidValue(float value){
		if( value > 0 ){
			return true;
		}
		return false;
	}

}


