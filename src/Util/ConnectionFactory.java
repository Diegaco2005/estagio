package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
	public static Connection getConnectionFactory() throws SQLException
	{
	   try{
		Class.forName("com.mysql.jdbc.Driver");
		//return DriverManager.getConnection("jdbc:mysql://192.168.2.19/Diego", "Diego", "Diego");
		return DriverManager.getConnection("jdbc:mysql://localhost/ads_estagio", "root", "7578");
		//return DriverManager.getConnection("jdbc:mysql://localhost/ads_estacionamento", "root", "7578");
	   }
	   catch(ClassNotFoundException e)
	   {
		  throw new SQLException(e.getMessage());
	   }
	}
}