package dataAccess.utility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaccia per il recupero dei dati necessari alla gestione dell'invio delle
 * email.
 * 
 * @author Gaetano
 * 
 */
public interface EmailDao {

	
	public HashMap<String, String> getContratto(String idContratto,
			String idCliente);

	public ArrayList<HashMap<String, String>> getIndirizzoCliente(String idContratto);
}
