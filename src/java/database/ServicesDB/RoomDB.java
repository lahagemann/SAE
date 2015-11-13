/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.ServicesDB;

import application.Domain.Room;
import java.sql.SQLException;
import java.util.List;

import database.Connection.ConnectionException;

/**
 *
 * @author Luiza
 */
public interface RoomDB {
    public void connect() throws SQLException, ConnectionException;
    
    public void disconnect() throws SQLException;
    
    public void insertRoom(Room r) throws SQLException, ConnectionException;
    
    public void deleteRoom(int roomID) throws SQLException, ConnectionException;
    
    public void updateRoom(Room r) throws SQLException, ConnectionException;
    
    public List<Room> getRoomList() throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException;
    
    public Room findRoomByID(int id) throws SQLException, ConnectionException, InconsistentDBException, DataNotFoundException;
}
