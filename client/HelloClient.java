package client;

import java.rmi.*;

import common.HelloInterface;

/**
 * This class represents the object client for a
 * distributed object of class Hello, which implements
 * the remote interface HelloInterface.  A security  
 * manager is installed to safeguard stub downloading.
 * @author M. L. Liu
 */

public class HelloClient {

  public static void main(String args[]) {
    try {      
      // start a security manager - this is needed if stub
      // downloading is in use for this application.
      // System.setSecurityManager(new SecurityManager());

      String registryURL = "rmi://localhost:1099/hello";  
      // find the remote object and cast it to an 
      //   interface object
      HelloInterface h = (HelloInterface)Naming.lookup(registryURL);
      System.out.println("Lookup completed " );
      // invoke the remote method
      String message = h.sayHello();
      System.out.println("HelloClient: " + message);
    } // end try 
    catch (Exception e) {
      System.out.println("Exception in HelloClient: " + e);
    } 
  } //end main
}//end class
