package presentazione;


import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import config.ConfiguratorReader;
import config.ConfiguratorSettingReader;
import javafx.scene.Scene;

/**
 * Si occupa di gestire la sessione del sistema.
 * 
 * @author Gianluca
 * 
 */
public class GestioneSessione {

	/**
	 * Scena corrente.
	 */
	private static Scene scenaCorrente = null;

	/**
	 * Competizione corrente.
	 */
	private static String idContrattoCorrente = null;
	/**
	 * Id dell'utente corrente.
	 */
	private static String idUtenteCorrente = null;

	/**
	 * Username dell'utente corrente.
	 */
	private static String usernameUtenteCorrente = null;

	/**
	 * Id dell'interfaccia corrente.
	 */
	private static String idInterfacciaCorrente = null;

	/**
	 * Id del Cliente di cui si stanno visualizzando delle informazioni.
	 */
	private static String idClienteCorrente = null;

	/**
	 * Id del manager di sistema di cui si stanno visualizzando delle
	 * informazioni.
	 */
	private static String idDipendenteCorrente = null;

	/**
	 * Id della Macchina di cui verranno visualizzate le informazioni.
	 */
	private static String idMacchinaCorrente = null;

	/**
	 * Id dell'Requisito di cui verranno visualizzate le informazioni.
	 */
	private static String idRequisitoCorrente = null;
	
	/**
	 * Limite temporale corrente.
	 */
	private static int limiteTemporaleCorrente = 48;

	/**
	 * Restituisce l'id del Cliente corrente.
	 * 
	 * @return id del Cliente corrente.
	 */
	public static String getIdClienteCorrente() {
		return idClienteCorrente;
	}

	/**
	 * Imposta l'id del Cliente corrente.
	 * 
	 * @param idClienteCorrente
	 *            l'id del Cliente corrente.
	 */
	public static void setIdClienteCorrente(String idClienteCorrente) {
		GestioneSessione.idClienteCorrente = idClienteCorrente;
	}

	/**
	 * Restituisce l'id del dipendente corrente.
	 * 
	 * @return l'id del dipendente corrente.
	 */
	public static String getIdDipendenteCorrente() {
		return idDipendenteCorrente;
	}

	/**
	 * Imposta l'id del dipendente corrente.
	 * 
	 * @param idDipendenteCorrente
	 *            l'id del dipendente corrente.
	 */
	public static void setIdDipendenteCorrente(
			String idDipendenteCorrente) {
		GestioneSessione.idDipendenteCorrente = idDipendenteCorrente;
	}

	/**
	 * Restituisce l'username dell'utente corrente.
	 * 
	 * @return l'username dell'utente corrente.
	 */
	public static String getUsernameUtenteCorrente() {
		return usernameUtenteCorrente;
	}

	/**
	 * Imposta l'utente corrente.
	 * 
	 * @param s
	 *            - l'username dell'utente corrente.
	 */
	public static void setUsernameUtenteCorrente(String s) {
		GestioneSessione.usernameUtenteCorrente = s;
	}

	/**
	 * Restituisce l'identificatore dell'interfaccia corrente.
	 * 
	 * @return l'id dell'interfaccia corrente.
	 */
	public static String getIdInterfacciaCorrente() {
		return idInterfacciaCorrente;
	}

	/**
	 * Imposta l'interfaccia corrente.
	 * 
	 * @param s
	 *            - l'id dell'interfaccia corrente.
	 */
	public static void setIdInterfacciaCorrente(String s) {
		GestioneSessione.idInterfacciaCorrente = s;
	}

	/**
	 * Restituisce l'id dell'utente corrente.
	 * 
	 * @return l'id dell'utente corrente.
	 */
	public static String getIdUtenteCorrente() {
		return idUtenteCorrente;
	}

	/**
	 * Imposta l'id dell'utente corrente.
	 * 
	 * @param s
	 *            - l'id dell'utente corrente.
	 */
	public static void setIdUtenteCorrente(String s) {
		GestioneSessione.idUtenteCorrente = s;
	}

	/**
	 * Restituisce l'id della competizione corrente.
	 * 
	 * @return l'id della competizione corrente.
	 */
	public static String getIdContrattoCorrente() {
		return idContrattoCorrente;
	}

	/**
	 * Imposta l'id della competizione corrente.
	 * 
	 * @param s
	 *            - l'id della competizione corrente.
	 */
	public static void setIdContrattoCorrente(String s) {
		GestioneSessione.idContrattoCorrente = s;
	}

	/**
	 * Restituisce la scena corrente della sessione.
	 * 
	 * @return la scena corrente.
	 */
	public static Scene getScenaCorrente() {
		return scenaCorrente;
	}

	/**
	 * Imposta la scena corrente della sessione.
	 * 
	 * @param scenaCorrente
	 *            la scena corrente da impostare.
	 */
	public static void setScenaCorrente(Scene scenaCorrente) {
		GestioneSessione.scenaCorrente = scenaCorrente;
	}

	/**
	 * Restituisce l'id Macchina corrente della sessione.
	 * 
	 * @return id della Macchina corrente.
	 */
	public static String getIdMacchinaCorrente() {
		return idMacchinaCorrente;
	}

	/**
	 * Imposta l'id Macchina corrente della sessione.
	 * 
	 * @param s
	 *            id della Macchina corrente.
	 */
	public static void setIdMacchinaCorrente(String s) {
		GestioneSessione.idMacchinaCorrente = s;
	}

	/**
	 * Restituisce l'id Requisito corrente della sessione.
	 * 
	 * @return id dell'Requisito corrente.
	 */
	public static String getIdRequisitoCorrente() {
		return idRequisitoCorrente;
	}

	/**
	 * Imposta l'id Requisito corrente della sessione.
	 * 
	 * @param s
	 *            - id Requisito corrente.
	 */
	public static void setIdRequisitoCorrente(String s) {
		GestioneSessione.idRequisitoCorrente = s;
	}
	
	/**
	 * Restituisce il limite temporale corrente della sessione.
	 * 
	 * @return id dell'Requisito corrente.
	 */
	public static int getLimiteTemporaleCorrente() {
		return limiteTemporaleCorrente;
	}

	/**
	 * Imposta il limite temporale corrente della sessione.
	 * Accede al file di configurazione per l'acquisizione del dato.
	 */
	public static void setLimiteTemporaleCorrente() {
		ConfiguratorReader reader = new ConfiguratorSettingReader();
		try {
			GestioneSessione.limiteTemporaleCorrente = Integer.parseInt(reader.getField(ConfiguratorSettingReader.LIMITE_TEMPORALE));
		} catch (Exception e) {
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(e.getClass(), e, GestioneErrori.NO_MESSAGE);
		}
	}

	/**
	 * Azzera lo stato della sessione.
	 */
	public static void azzera() {
		GestioneSessione.idContrattoCorrente = null;
		GestioneSessione.idInterfacciaCorrente = null;
		GestioneSessione.idUtenteCorrente = null;
		GestioneSessione.usernameUtenteCorrente = null;
		GestioneSessione.idClienteCorrente = null;
		GestioneSessione.idDipendenteCorrente = null;
		GestioneSessione.idMacchinaCorrente = null;
		GestioneSessione.idRequisitoCorrente = null;
	}
}
