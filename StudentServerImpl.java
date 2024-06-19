/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 6
*/
package LAB6;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.lang.model.element.NestingKind;

public class StudentServerImpl extends UnicastRemoteObject implements StudentServerInterface {
	
	public StudentServerImpl() throws RemoteException {
		super();
	}

	@Override
	public String searchScore(String name) throws RemoteException,SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student" , "root", "Natalie@78");
		System.out.println("Database connected");
		//Create a statement
		String queryString = "select scores, permission from Scores where stud_name = ? ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		preparedStatement.setString(1, name);
		ResultSet resultSet = preparedStatement.executeQuery();
		//Iterate through the result and print the student names
		String getScore = "0";
		String getpermission = "0";
		while (resultSet.next()) {
			getScore = resultSet.getString(1);
			getpermission = resultSet.getString(2);}
		//Close the connection
		connection.close();
		if(getpermission.toLowerCase().equals("no"))
			return "Permission Not GRANTED";
		
		return getScore;
	}
	
	public static void main(String [] args) throws RemoteException, AlreadyBoundException, ClassNotFoundException {
		Registry registry = LocateRegistry.createRegistry(1199);
		registry.rebind("Studentinterface", new StudentServerImpl());
		System.out.println("The STUDENT_INTERFACE_IMPL is ready and running");
	}

}

