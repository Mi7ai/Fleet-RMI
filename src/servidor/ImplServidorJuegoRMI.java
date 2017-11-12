package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.ListIterator;

import comun.IntCallbackCliente;
import comun.IntServidorJuegoRMI;
import comun.IntServidorPartidasRMI;

public class ImplServidorJuegoRMI extends UnicastRemoteObject implements IntServidorJuegoRMI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,IntCallbackCliente> mapaPartidas;// estructura donde se guardan las partidas

	public ImplServidorJuegoRMI() throws RemoteException{
		super();
		mapaPartidas = new HashMap<>();
	}

	public synchronized boolean proponerPartida(String nombreJugador, IntCallbackCliente callbackCliente) throws RemoteException {
		if (mapaPartidas.containsKey(nombreJugador)) {  
			System.out.println(nombreJugador+": ha propuesto una partida pero estaba en una.");
			return false;
		}
		mapaPartidas.put(nombreJugador, callbackCliente);
		System.out.println(nombreJugador+": ha propuesto una partida.");
		return true;
	}

	public synchronized boolean aceptarPartida(String nombreJugador, String nombreOponente) throws RemoteException {
		// TODO Auto-generated method stub
		if (!mapaPartidas.containsKey(nombreOponente)) {//Si el rival no ha propuesto partida devuelve false
			System.out.println(nombreJugador+" ha intentado aceptar una partida de "+nombreOponente+", pero este no tiene ninguna.");
			return false;
		}
		if (mapaPartidas.containsKey(nombreJugador)) {
			System.out.println("No puedes aceptar tu propria partida.");
			return false;
		}
		try{
			mapaPartidas.get(nombreOponente).notificar(nombreJugador); //Intenta comumnicarse con el jugador mediante callback, si no false
		}catch(Exception e){
			System.out.println("El oponente "+ nombreOponente+" no esta y su partida se ha va a borrar.");
			return false;
		}finally {
			mapaPartidas.remove(nombreOponente); // se eliminara la partida porque el jugador no la ha aceptado 
		}
		System.out.println(nombreJugador+" y "+nombreOponente+" estan jugando con ganas.");
		return true;
	}
	
	public synchronized boolean borrarPartida(String nombreJugador) throws RemoteException {
 		if (mapaPartidas.containsKey(nombreJugador)){ //si el jugador esta en una partida  
			mapaPartidas.remove(nombreJugador);
			System.out.println(nombreJugador+" ha borrado su partida.");
			return true;
		}
		System.out.println(nombreJugador+" no tenia ninguna.");
		return false;
	}


	public synchronized String[] mostrarPartidas() throws RemoteException {
		int i=0;
		System.out.println("Lista de partidas propuestas.");
 		 
		String[] lista = new String[mapaPartidas.size()];
		
		for (String partida : mapaPartidas.keySet()){// recorrer las partidas
			lista[i] = partida;
			i++;
		}
		
		
		
		
		return lista;
	}
	public synchronized void destruir() throws RemoteException{
		mapaPartidas.clear();
	}
	
	

	public IntServidorPartidasRMI nuevoServidorPartidas() throws RemoteException {
 		return new ImplServidorPartidasRMI();
	}	

}
