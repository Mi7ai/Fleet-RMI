package server;

import java.rmi.*;
import java.rmi.server.*;

import common.HelloInterface;

/**
 * This class implements the remote interface HelloInterface.
 * @author M. L. Liu
 */

public class HelloImpl extends UnicastRemoteObject implements HelloInterface {

	private static final long serialVersionUID = 1L;

	public HelloImpl() throws RemoteException {
		super( );
	}

	public String sayHello() throws RemoteException {
		return "Hello, World!";
	}
} // end class
