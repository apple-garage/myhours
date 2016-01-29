package com.applegarage.reportes.elreportador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SuperConexionClass {
	
	String dbUrl = "jdbc:mysql://localhost:8090/reportes";
	String dbClass = "com.mysql.jdbc.Driver";
	String username = "root";
	String password = "martin";
	
	public SuperConexionClass(){
		
	}
	
	public Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(dbUrl,
				username, password);
		return connection;
	}

	public void test(){
		

		String query = "select * from empleados;";
		
		try {
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl,
			username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				String empName = resultSet.getString(1);
				System.out.println("Employee name : " + empName);
			}
			
			connection.close();
			
		} catch (SQLException e){
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
