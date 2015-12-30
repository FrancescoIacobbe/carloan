package config;

/**
 * Carica il limite temporale di iscrizione alle competizioni dal file di configurazione.
 * @author Gianluca
 *
 */
public class ConfiguratorSettingReader implements ConfiguratorReader {
	
	private GestioneProperties gp;

	/**
	 * Parametro limiteTemporale.
	 */
	public static final String LIMITE_TEMPORALE = "limiteTemporale";

	private final static String PATH = "src/config/setting.cfg.txt";

	/**
	 * Costruttore principale.
	 */
	public ConfiguratorSettingReader() {
		gp = new GestioneProperties(PATH);
	}
	
	@Override
	public String getField(String key) {
		// TODO Auto-generated method stub
		return gp.getValue(key);
	}

}
