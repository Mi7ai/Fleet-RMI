package comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntServidorPartidasRMI extends Remote{
	// crear nueva partida al cuando se pida
	public void nuevaPartida(int nf, int nc, int nrBarcos)	throws RemoteException;
	
	// prueba el estado de una casilla y devuelve AGUA, TOCADO, HUNDIDO
	public int pruebaCasilla(int nf, int nc)	throws RemoteException;
	
	// devuelve un barco identificado por su id
	public String getBarco(int idBarco)	throws RemoteException;
	
	// muestra la solucion en el tablero
	public String[] getSolucion()	throws RemoteException;
}
