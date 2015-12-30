package business;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

import dataAccess.factory.DaoFactory;
import dataAccess.utility.EmailDao;
import business.messages.Messaggio;
import business.messages.MessaggioBenvenuto;
import business.messages.MessaggioBenvenutoDipendente;
import business.messages.MessaggioDatiAccount;
import business.messages.MessaggioNuovoContratto;
import business.messages.MessaggioModificaContratto;

/**
 * Application service per l'invio delle email.
 * 
 * @author Gaetano
 * 
 */
public class GestioneEmail {

	private EmailDao dao;

	/**
	 * Costruttore principale istanzia il Dao di riferimento.
	 */
	public GestioneEmail() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL).getEmailDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}

	/**
	 * Invia una mail di conferma al partecipante che ha effettuato la
	 * registrazione.
	 * 
	 * @param email
	 *            - email del partecipante.
	 * @param nome
	 *            - nome del partecipante.
	 */
	public void registrazione(String email, String nome, String username,
			String password) {
		HashMap<String, String> indirizzo = new HashMap<String, String>(1);
		indirizzo.put("email", email);
		indirizzo.put("nome", nome);
		ArrayList<HashMap<String, String>> dest = new ArrayList<HashMap<String, String>>(
				1);
		dest.add(indirizzo);
		Messaggio mess = new MessaggioBenvenuto(Messaggio.BACKGROUND, dest, username, password);
		mess.send();
	}


	/**
	 * Invia una email al mananger della competizione per avvisare dell'avvenuta
	 * iscrizione da parte di un partecipante.
	 * 
	 * @param idCompetizione
	 *            - id della competizione a cui il partecipante si è iscritto.
	 * @param idPartecipante
	 *            - id del partecipante.
	 */
	public void nuovoContratto(String idContratto, String idCliente) {
		ArrayList<HashMap<String, String>> dest = new ArrayList<HashMap<String, String>>();
		dest.add(dao.getContratto(idContratto, idCliente));
		if (dest.size() > 0) {
			String contratto = dest.get(0).get("contratto");
			String cliente = dest.get(0).get("cliente");
			String emailCliente = dest.get(0).get("emailCliente");
			Messaggio mess = new MessaggioNuovoContratto(Messaggio.BACKGROUND, dest,
					contratto, cliente);
			mess.send();
		}
	}



	/**
	 * Invia una email a tutti i partecipanti iscritti ad una determinata
	 * competizione per notificare eventuali modifiche apportate alla
	 * competizione.
	 * 
	 * @param idCompetizione l'id della competizione mofificata.
	 * @param motivazione la motivazione della modifica.
	 */
	public void modificaContratto(String idContratto, String motivazione) {
		ArrayList<HashMap<String, String>> dest = new ArrayList<HashMap<String, String>>();
		dest.addAll(dao.getIndirizzoCliente(idContratto));
		if(dest.size() > 0) {
			Messaggio mess = new MessaggioModificaContratto(Messaggio.VISIBLE, dest, idContratto, motivazione);
			mess.send();
		}
	}
	
	/**
	 * Invia una mail ad una determinato partecipante con i nuovi dati Account.
	 * @param email - email del partecipante.
	 * @param username - username del partecipante.
	 * @param password - password del partecipante.
	 */
	public void modificaAccount(String email, String username, 
			String password) {
		HashMap<String, String> indirizzo = new HashMap<String, String>(1);
		indirizzo.put("email", email);
		indirizzo.put("nome", username);
		ArrayList<HashMap<String, String>> dest = new ArrayList<HashMap<String, String>>(1);
		dest.add(indirizzo);
		Messaggio mess = new MessaggioDatiAccount(Messaggio.BACKGROUND, dest, username, email, password);
		mess.send();
	}
	
	public void registrazioneDipendente(String email, String nome, String username,
			String password) {
		HashMap<String, String> indirizzo = new HashMap<String, String>(1);
		indirizzo.put("email", email);
		indirizzo.put("nome", nome);
		ArrayList<HashMap<String, String>> dest = new ArrayList<HashMap<String, String>>(
				1);
		dest.add(indirizzo);
		Messaggio mess = new MessaggioBenvenutoDipendente(Messaggio.BACKGROUND, dest, username, password);
		mess.send();		
	}
	
	
}
