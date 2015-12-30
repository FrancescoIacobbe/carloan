package dataAccess.utility;

/**
 * Interfaccia per il recupero dei dati necessari al funzionamento dell'utility
 * di auto-completamento.
 * 
 * @author Gianluca
 * 
 */
public interface AutoTextDao {

	/**
	 * Fornisce la miglior corrispondenza con key nella tabella table per il
	 * campo field.
	 * 
	 * @param key
	 *            la chiave di ricerca.
	 * @param table
	 *            la tabella in cui cercare.
	 * @param field
	 *            il campo della tabella in cui cercare.
	 * @return la miglior corrsipondenza se trovata, altrimenti null.
	 */
	public String autoText(String key, String table, String field);
}
