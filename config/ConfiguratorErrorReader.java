package config;

/**
 * Legge i messaggi di errore inseriti nel file di configurazione.
 * @author Gaetano
 *
 */
public class ConfiguratorErrorReader implements ConfiguratorReader{

	private GestioneProperties gs;
	private final static String PATH = "src/config/error.cfg.txt";
	
	/**
	 * Costruttore principale
	 */
	public ConfiguratorErrorReader() {
		//gs = new GestioneProperties(PATH);
	}
	
	@Override
	public String getField(String key) {
		// TODO Auto-generated method stub
		return "ciao";//gs.getValue(key);
	}

}
