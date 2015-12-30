package config;

/**
 * Legge i valori inerenti la configurazione del gestore delle email dal file di configurazione.
 * @author Gianluca
 *
 */
public class ConfiguratorSMTPReader implements ConfiguratorReader {

	private GestioneProperties gp;

	/**
	 * Parametro username.
	 */
	public static final String USERNAME = "usernameSMTP";

	/**
	 * Parametro password.
	 */
	public static final String PASSWORD = "passwordSMTP";

	/**
	 * Parametro host.
	 */
	public static final String HOST = "hostSMTP";

	/**
	 * Parametro port.
	 */
	public static final String PORT = "portSMTP";

	/**
	 * Costruttore principale.
	 * @param path percorso del file di configurazione.
	 */
	public ConfiguratorSMTPReader(String path) {
		this.gp = new GestioneProperties(path);
	}

	@Override
	public String getField(String key) {
		// TODO Auto-generated method stub
		return gp.getValue(key);
	}

}
