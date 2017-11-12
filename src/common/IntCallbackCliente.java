package comun;

import java.rmi.RemoteException;

public interface IntCallbackCliente {
	public void notificar(String nombre) throws RemoteException;

}
