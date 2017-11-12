package cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import comun.IntCallbackCliente;

public class ImplCallbackCliente extends UnicastRemoteObject implements IntCallbackCliente{

	protected ImplCallbackCliente() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notificar(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(nombre+" acaba de aceptar la partida propuesta");

	}

}
