package business;

import java.util.ArrayList;
import java.util.HashMap;

import utility.Security;
import entity.LManagerSistemaInt;
import entity.ManagerSistema;
import entity.SManagerSistemaInt;

/**
 * Application Service per i servizi inerenti il manager del sistema.
 * @author Gianluca
 *
 */
public class GestioneManagerSistema {

	private LManagerSistemaInt lManager;
	private SManagerSistemaInt sManager;
	
	/**
	 * Fornisce i dati account del manager di sistema.
	 * @return un {@link HashMap} con i dati account.
	 */
	public HashMap<String,String> getAccount() {
		lManager = new ManagerSistema();
		HashMap<String,String> result = lManager.getAccountManager();
		return result;
	}
	
	/**
	 * Imposta i dati account del manager di sistema.
	 * @param p contiene:
	 * 					1) email
	 * 					2) password
	 * @return true sse l'aggiornamento va a buon fine, altrimenti false.
	 */
	public boolean setAccount(ArrayList<String> p) {
		String password = p.get(1);
		password = Security.cipher(password);
		String email = p.get(0);
		sManager = new ManagerSistema();
		boolean controll = sManager.setAccountManager(password, email);
		return controll;
	}
}
