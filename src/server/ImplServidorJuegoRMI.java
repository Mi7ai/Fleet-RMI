package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import comun.IntCallbackCliente;
import comun.IntServidorJuegoRMI;
import comun.IntServidorPartidasRMI;

public class ImplServidorJuegoRMI extends UnicastRemoteObject implements IntServidorJuegoRMI{
	private HashMap<String,IntCallbackCliente> mapaPartidas;

	public ImplServidorJuegoRMI() throws RemoteException{
		super();
		mapaPartidas = new HashMap<>();
	}


	public synchronized boolean proponerPartida(String nombreJugador, IntCallbackCliente callbackObjetoCliente) throws RemoteException {
		// TODO Auto-generated method stub
		if (mapaPartidas.containsKey(nombreJugador)) {//Si hay una partida propuesta se indica 
			System.out.println(nombreJugador+" ha propuesto una partida ya pero estaba en una.");
			return false;
		}
		mapaPartidas.put(nombreJugador, callbackObjetoCliente);
		System.out.println(nombreJugador+" ha propuesto una partida.");
		return true;
	}


	public synchronized boolean borrarPartida(String nombreJugador) throws RemoteException {
		// TODO Auto-generated method stub
		if (mapaPartidas.containsKey(nombreJugador)){ //si el jugador esta en una partida devuelve true
			mapaPartidas.remove(nombreJugador);
			System.out.println(nombreJugador+" ha borrado su partida.");
			return true;
		}
		System.out.println(nombreJugador+" ha intentado borrar su partida, pero no tenia ninguna.");
		return false;
	}


	public synchronized String[] mostrarPartidas() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Lista de partidas propuestas.");
		String[] lista = new String[mapaPartidas.size()];
		int i=0;
		for (String partida : mapaPartidas.keySet()){
			lista[i++]=partida;
		}
		return lista;
	}


	public synchronized boolean aceptarPartida(String nombreJugador, String nombreOponente) throws RemoteException {
		// TODO Auto-generated method stub
		if (!mapaPartidas.containsKey(nombreOponente)) {//Si el rival no ha propuesto partida devuelve false
			System.out.println(nombreJugador+" ha intentado aceptar una partida de "+nombreOponente+", pero este no tiene ninguna.");
			return false;
		}
		try{
			mapaPartidas.get(nombreOponente).notificar(nombreJugador); //Intenta comumnicarse con el jugador mediante callback, si no false
		}catch(Exception e){
			System.out.println("El oponente "+ nombreOponente+" no esta y su partida se ha borrada.");
			return false;
		}finally {
			mapaPartidas.remove(nombreOponente); //Pase lo que pase, se elimina la partida, ya que o se acepta o el jugador no esta activo
		}
		System.out.println(nombreJugador+" y "+nombreOponente+" estan jugando con ganas.");
		return true;
	}

	public IntServidorPartidasRMI nuevoServidorPartidas() throws RemoteException {
		// TODO Auto-generated method stub
		return new ImplServidorPartidasRMI();
	}	

}
