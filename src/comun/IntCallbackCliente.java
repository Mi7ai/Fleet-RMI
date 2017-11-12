package comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntCallbackCliente extends Remote{
	public void notificar(String nombre) throws RemoteException;//metodo para llamar al callback cuando sea necesario

}
