package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;
import dataAccess.entity.UtenteDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi per l'autenticazione e la registrazione di un utente.
 * 
 * @author Gianluca
 * 
 */
public class Utente implements AutenticazioneInt, RegistrazioneInt {

	private UtenteDao dao;

	private static String username;
	private static String password;
	private static String email;

	/**
	 * Costruttore principale.
	 * Istanzia il dao di riferimento.
	 */
	public Utente() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getUtenteDao();
		} catch (InstantiationException | IllegalAccessException e) {
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}

	@Override
	public String login(String username, String password) {
		return dao.login(username, password);
	}

	@Override
	public boolean isAvailableUsername(String username) {
		return dao.isAvailableUsername(username);
	}

	@Override
	public boolean isAvailableEmail(String email) {
		return dao.isAvailableEmail(email);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public void setUsername(String newUsername) {
		// TODO Auto-generated method stub
		Utente.username = newUsername;
	}

	@Override
	public void setPassword(String newPassword) {
		// TODO Auto-generated method stub
		Utente.password = newPassword;
	}

	@Override
	public void setEmail(String newEmail) {
		// TODO Auto-generated method stub
		Utente.email = newEmail;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

}
