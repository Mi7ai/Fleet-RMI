package server;

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;

/**
 * This class represents the object server for a distributed
 * object of class Hello, which implements the remote 
 * interface HelloInterface.  A security manager is
 * installed to safeguard stub downloading.
 * @author M. L. Liu
 */

public class HelloServer  {
   public static void main(String args[]) {
      try{     
         // start a security manager - this is needed if
         // stub downloading is in use for this application.
         // System.setSecurityManager(new SecurityManager());

         startRegistry(1099);
         HelloImpl exportedObj = new HelloImpl();
         String registryURL = "rmi://localhost:1099/hello";
         Naming.rebind(registryURL, exportedObj);
         System.out.println("Server registered. Registry contains:");
         // list names currently in the registry
         listRegistry(registryURL);
         System.out.println("Hello Server ready.");
      }// end try
      catch (Exception re) {
         System.out.println("Exception in HelloServer.main: " + re);
      } // end catch
  } // end main

  // This method starts a RMI registry on the local host, if
  // it doesn't already exists at the specified port number.
   private static void startRegistry(int RMIPortNum)
      throws RemoteException{
      try {
         Registry registry = 
            LocateRegistry.getRegistry(RMIPortNum);
         registry.list();  // This call will throw an
         //exception if the registry does not already exist
      } catch (RemoteException e) { 
         // No valid registry at that port.
         System.out.println("RMI registry cannot be located at port " + RMIPortNum);
         LocateRegistry.createRegistry(RMIPortNum);
         System.out.println("RMI registry created at port " + RMIPortNum);
      }
   } // end startRegistry

  //This method lists the names registered with a Registry
  private static void listRegistry(String registryURL)
       throws RemoteException, MalformedURLException {
       System.out.println("Registry " + registryURL + " contains: ");
       String [] names = Naming.list(registryURL);
       for (int i=0; i < names.length; i++)
          System.out.println(names[i]);
  } //end listRegistry
     
} // end class
