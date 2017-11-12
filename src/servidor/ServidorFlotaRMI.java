package servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
		
public class ServidorFlotaRMI {
	public static void main (String args[]){

		try{
//			if (System.getSecurityManager()==null){
//				System.setProperty("java.security.policy","file:/G:/Installed Programs/eclipse/WorkspaceJava2/FleetRMI/src/servidor/java.policy");

//				System.setSecurityManager(new SecurityManager());

//			}
			
			
			startRegistry(1099);
			ImplServidorJuegoRMI exportedObj = new ImplServidorJuegoRMI();
			String registryURL = "rmi://localhost:1099/FleetRMI";
			Naming.rebind(registryURL,  exportedObj);
			System.out.println("FlotaRMI Server lista.");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in file ServidorFlotaRMI: " + e);
		}
	}
	
	private static void startRegistry(int RMIPortNum) throws RemoteException{
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
		    registry.list();  
		} catch (RemoteException e) {
		    System.out.println("RMI registry cannot be located at port " + RMIPortNum);
		    LocateRegistry.createRegistry(RMIPortNum);
		    System.out.println("RMI registry created at port " + RMIPortNum);
		}
	}
	
 
}
