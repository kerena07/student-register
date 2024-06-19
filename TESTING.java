package LAB6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TESTING {
	public static void main(String[]args) throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student" , "root", "Natalie@78");
		System.out.println("Database connected");
	    
	}
}
