package dataAccess.entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface ClienteDao {

	public HashMap<String, String> getDatiCliente(String username);

	public ArrayList<HashMap<String, String>> getContrattoCliente(String id);

	public HashMap<String, String> getAnagraficaCliente(String username);

	public HashMap<String, String> getAccountCliente(String username);

	public boolean setAnagraficaCliente( String nome, String cognome,
			String dataNascita, String CF,
			String città, String indirizzo, String cap, String numTelefono) ;
	
	public boolean setAccountCliente(String username, String email, String password);

	public ArrayList<HashMap<String, String>> ricercaContratti(String id,
			String idContratto) ;

	public ArrayList<HashMap<String, String>> getClientiSistema();

	public ArrayList<HashMap<String, String>> ricercaClienti(String key);

	public boolean registraCliente(String username, String password,
			String email, String nome, String cognome, String dataNascita,
			String cF, String città, String indirizzo, String cap,
			String numTelefono) ;
	

}
