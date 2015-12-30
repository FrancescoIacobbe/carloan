package business;

import java.util.ArrayList;
import java.util.HashMap;

import utility.Security;
import entity.AutenticazioneInt;
import entity.RegistrazioneInt;
import entity.Utente;

/**
 * Application Service per i servizi inerenti la registrazione e l'accesso al
 * sistema.
 * 
 * @author Gianluca
 * 
 */
public class GestioneAccesso {

	private AutenticazioneInt utenteAut;
	private RegistrazioneInt utenteReg;

	/**
	 * Esegue il login.
	 * 
	 * @param p
	 *            contiene username e la password.
	 * @return il tipo di utente autenticato o null se la combinazione
	 *         username-password non è presente nel sistema.
	 */
	public ArrayList<String> login(ArrayList<String> p) {
		String username = p.get(0);
		String password = Security.cipher(p.get(1));
		utenteAut = new Utente();
		String res = utenteAut.login(username, password);
		p.clear();
		p.add(res);
		p.add(username);
		return p;
	}

	/**
	 * Verifica che un username sia disponibile per una nuova registrazione.
	 * 
	 * @param p
	 *            contiene l'username da verificare.
	 * @return true se l'username è disponibile, altrimenti false.
	 */
	public boolean isAvailableUsername(ArrayList<String> p) {
		String username = p.get(0);
		utenteReg = new Utente();
		return utenteReg.isAvailableUsername(username);
	}

	/**
	 * Verifica che una email sia disponibile per una nuova registrazione.
	 * 
	 * @param p
	 *            contiene l'email da verificare.
	 * @return true se l'email è disponibile, altrimenti false.
	 */
	public boolean isAvailableEmail(ArrayList<String> p) {
		String email = p.get(0);
		utenteReg = new Utente();
		return utenteReg.isAvailableEmail(email);
	}

	/**
	 * Fornisce il servizio di registrazione di un nuovo partecipante. Salva in
	 * memoria l'username e la password del nuovo partecipante iscritto.
	 * 
	 * @param p
	 *            contiene: 1) l'username. 2) password.
	 */
	public void nuovoAccount(ArrayList<String> p) {
		utenteReg = new Utente();
		int i = 0;
		String username = p.get(i++);
		String password = p.get(i++);
		String email = p.get(i++);
		utenteReg.setPassword(password);
		utenteReg.setUsername(username);
		utenteReg.setEmail(email);
	}

	/**
	 * Fornisce username e password mantenuti in memoria durante la fase di
	 * registrazione.
	 * 
	 * @return un dizionaio contenente username e password.
	 */
	public HashMap<String, String> getDatiRegistrazione() {
		utenteReg = new Utente();
		String username = utenteReg.getUsername();
		String password = utenteReg.getPassword();
		String email = utenteReg.getEmail();
		HashMap<String, String> dati = new HashMap<String, String>();
		dati.put("username", username);
		dati.put("password", password);
		dati.put("email", email);
		return dati;
	}
}
