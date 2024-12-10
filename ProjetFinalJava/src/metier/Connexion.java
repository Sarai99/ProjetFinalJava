package metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static Connection  con;
	  private static String driver="com.mysql.jdbc.Driver";
	  private static String url="jdbc:mysql://localhost:3306/db_Banque2022";
	  private static String user="root";
	  private static String password="023156";
		static {
			    
		    try {
				Class.forName(driver);
				con=DriverManager.getConnection(url , user , password);
			} catch (ClassNotFoundException e) {
					e.printStackTrace();
			} catch (SQLException e) {
					e.printStackTrace();
			}
		   }
		   
		   public static Connection getConnection()
		   {
			 return con;  
		   }

}
