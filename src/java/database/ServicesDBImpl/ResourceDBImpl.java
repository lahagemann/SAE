/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDBImpl;

import application.Domain.Resource;
import database.Connection.ConnectionException;
import database.Connection.ConnectionFactory;
import database.ServicesDB.DataNotFoundException;
import database.ServicesDB.ResourceDB;
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
public class ResourceDBImpl implements ResourceDB {

	private Connection connection = null;
	private Statement statement = null;

	public ResourceDBImpl() {
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

	@Override
	public void insertResource(Resource r) throws SQLException, ConnectionException {
		String DateTime = null;
		String query;

		if (r.isOn()) {
			DateTime = DateConversion.DateConvert(r.getTurnOnTime()) + " " + DateConversion.TimeConvert(r.getTurnOnTime());
			query = "INSERT INTO resource (name, type, consumption, resourceOn, resourceBroken, turnOnTime, idRoom) VALUES "
					+ "('" + r.getName() + "', '" + r.getType() + "', " + r.getConsumption() + ", " + r.isOnINT()
					+ "' " + r.isResourceBrokenINT() + ", " + DateTime + ", " + r.getLocationID() + ");";
		} else {
			query = "INSERT INTO resource (name, type, consumption, resourceOn, resourceBroken, turnOnTime, idRoom) VALUES "
					+ "('" + r.getName() + "', '" + r.getType() + "', " + r.getConsumption() + ", " + r.isOnINT()
					+ ", " + r.isResourceBrokenINT() + ", null, " + r.getLocationID() + ");";
		}
		connect();
		statement.executeUpdate(query);

		disconnect();
	}

	@Override
	public void deleteResource(int resourceID) throws SQLException, ConnectionException {

		String query = "DELETE FROM resource WHERE identifier = " + resourceID + ";";

		connect();
		statement.executeUpdate(query);

		disconnect();
	}

	@Override
	public void updateResource(Resource r) throws SQLException, ConnectionException {
		String DateTime = null;
		String query;
		if (r.isOn()) {
			DateTime = DateConversion.DateConvert(r.getTurnOnTime()) + " " + DateConversion.TimeConvert(r.getTurnOnTime());
			query = "UPDATE resource SET name = '" + r.getName() + "', type = '" + r.getType() + "', resourceOn = " + r.isOnINT()
					+ ", consumption = " + r.getConsumption() + ", turnOnTime = '" + DateTime + "', resourceBroken = " + r.isResourceBrokenINT()
					+ ", idRoom = " + r.getLocationID() + " WHERE identifier = " + r.getIdentifier() + ";";
		} else {
			query = "UPDATE resource SET name = '" + r.getName() + "', type = '" + r.getType() + "', resourceOn = " + r.isOnINT()
					+ ", consumption = " + r.getConsumption() + ", turnOnTime = null , resourceBroken = " + r.isResourceBrokenINT()
					+ ", idRoom = " + r.getLocationID() + " WHERE identifier = " + r.getIdentifier() + ";";
		}

		connect();
		statement.executeUpdate(query);

		disconnect();
	}

	@Override
	public Resource findResourceByID(int ID) throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		String query = "SELECT * FROM resource WHERE identifier = " + ID + ";";
		String name, type;
		boolean resourceOn, resourceBroken;
		float consumption;
		int identifier, locationID;
		Date turnOnTime;

		connect();
		try{

			resultset = statement.executeQuery(query);
			resultset.next();

			name = resultset.getString("name");
			identifier = resultset.getInt("identifier");
			type = resultset.getString("type");
			consumption = resultset.getFloat("consumption");
			resourceOn = resultset.getBoolean("resourceOn");
			resourceBroken = resultset.getBoolean("resourceBroken");

			turnOnTime = new Date();
			Timestamp timestamp = resultset.getTimestamp("turnOnTime");
			if (timestamp != null) {
				turnOnTime = new java.util.Date(timestamp.getTime());
			}

			locationID = resultset.getInt("idRoom");

		} catch(SQLException exp){
			disconnect();
			throw new DataNotFoundException("Recurso não foi encontrado.");
		}
		Resource aux = new Resource(name,
				identifier,
				type,
				consumption,
				resourceOn,
				resourceBroken,
				turnOnTime,
				locationID);

		disconnect();
		return aux;
	}

	@Override
	public List<Resource> findResourceByRoom(int room) throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		List<Resource> results = new ArrayList<Resource>();
		String query = "SELECT * FROM resource WHERE idRoom = " + room + ";";
		String name, type;
		boolean resourceOn, resourceBroken;
		float consumption;
		int identifier, locationID;
		Date turnOnTime;
		
		try{
			connect();

			resultset = statement.executeQuery(query);

			while (resultset.next()) {

				name = resultset.getString("name");
				identifier = resultset.getInt("identifier");
				type = resultset.getString("type");
				consumption = resultset.getFloat("consumption");
				resourceOn = resultset.getBoolean("resourceOn");
				resourceBroken = resultset.getBoolean("resourceBroken");

				turnOnTime = new Date();
				Timestamp timestamp = resultset.getTimestamp("turnOnTime");
				if (timestamp != null) {
					turnOnTime = new java.util.Date(timestamp.getTime());
				}

				locationID = resultset.getInt("idRoom");

				Resource aux = new Resource(name,
						identifier,
						type,
						consumption,
						resourceOn,
						resourceBroken,
						turnOnTime,
						locationID);
				results.add(aux);
			}
		} catch(SQLException exp){
			throw new DataNotFoundException("A sala não tem recursos.");
		}
		finally{
			disconnect();
		}

		return results;
	}

	@Override
	public List<Resource> getAllResources() throws SQLException, ConnectionException, DataNotFoundException {
		ResultSet resultset = null;
		List<Resource> results = new ArrayList<Resource>();
		String query = "SELECT * FROM employee";
		String name, type;
		boolean resourceOn, resourceBroken;
		float consumption;
		int identifier, locationID;
		Date turnOnTime;
		
		try{ 
		connect();
		resultset = statement.executeQuery(query);
		while (resultset.next()) {
			name = resultset.getString("name");
			identifier = resultset.getInt("identifier");
			type = resultset.getString("type");
			consumption = resultset.getFloat("consumption");
			resourceOn = resultset.getBoolean("resourceOn");
			resourceBroken = resultset.getBoolean("resourceBroken");

			turnOnTime = new Date();
			Timestamp timestamp = resultset.getTimestamp("turnOnTime");
			if (timestamp != null) {
				turnOnTime = new java.util.Date(timestamp.getTime());
			}

			locationID = resultset.getInt("idRoom");
			Resource aux = new Resource(name, identifier, type, consumption, resourceOn, resourceBroken, turnOnTime, locationID);

			results.add(aux);

		}
		} catch(SQLException exp){
			throw new DataNotFoundException("Não existe recursos.");
		} finally{
			disconnect();
		}
		return results;
	}
}
