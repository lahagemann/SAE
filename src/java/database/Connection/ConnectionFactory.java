/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jessica
 */
public class ConnectionFactory {

    public static Connection getConnection() throws ConnectionException {

        String servidor = "jdbc:mysql://localhost/test";
        String usuario = "root";
        String senha = "merda";
        String driver = "com.mysql.jdbc.Driver";
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(servidor, usuario, senha);
        	
            return connection;

        }catch (Exception e){
        	throw new ConnectionException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}
