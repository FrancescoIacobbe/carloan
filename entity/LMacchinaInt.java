package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia che fornaisce i servizi di lettura per gli optional.
 * 
 * @author Gaetano
 * 
 */
public interface LMacchinaInt {
	
	
	/**
	 * Fornisce una lista con gli optional attivi nel sistema.
	 * @return un {@link ArrayList} di {@link HashMap} che rappresentano gli optional.
	 */
	public ArrayList<HashMap<String, String>> getMacchinaSistema();

	/**
	 * Fornisce i dati relativi ad un determinato tipo di optional presente nel
	 * sistema.
	 * @param idOptional - id dell'optional.
	 * @return - un dizionario contenente i dati relativi all'optional.
	 */
	public HashMap<String, String> getDatiMacchina(String targa);

	/**
	 * Fornisce un elenco con gli optional del sistema che rispettano il parametro di ricerca.
	 * @param key il parametro di ricerca.
	 * @return un {@link ArrayList} di {@link HashMap} contenenti le informazioni sugli optional.
	 */
	public ArrayList<HashMap<String, String>> ricercaMacchine(String key);

}
