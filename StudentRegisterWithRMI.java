
/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 6
*/
package LAB6;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class StudentRegisterWithRMI implements Remote {
	public static void main(String[] args) {
		try {
			StudentServerImpl obj = new StudentServerImpl() ;
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("StudentServerInterfaceImpl",  obj);
			System.out.println(" SERVER " + obj + " CONNECTED");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}

}
