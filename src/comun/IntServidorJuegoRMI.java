package comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntServidorJuegoRMI extends Remote{
	// el jugador propone una partida y recibe un callback si el otro cliente la acepta
	public boolean proponerPartida( String nombreJugador, IntCallbackCliente callbackClientObject) throws RemoteException;

	//	acepta partidas propuestas
	public boolean aceptarPartida(String nombreJugador, String nombreRival) throws RemoteException;

	// elimina su registro para callback
	public boolean borrarPartida( String nombreJugador) throws RemoteException;

	//	muestra las partidas propuestas
	public String[] mostrarPartidas() throws RemoteException;

	public void destruir() throws RemoteException; // borra el contenido del hashmap
	
	//	enlace interfaz  partida
	public IntServidorPartidasRMI nuevoServidorPartidas() throws RemoteException;
}
