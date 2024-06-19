/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 6
*/
package LAB6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer1 {
	public static void main(String[] args) throws RemoteException {
		LocateRegistry.createRegistry(1099);
		while(true);
	}
}
