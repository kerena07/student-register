/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 6
*/
package LAB6;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface StudentServerInterface extends Remote{
	public String searchScore(String name) throws RemoteException,SQLException, ClassNotFoundException;

}
