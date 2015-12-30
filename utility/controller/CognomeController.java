package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue il controllo di un cognome.
 * Il cognome deve avere lunghezza compresa tra i 2 e i 15 caratteri.
 * @author Gianluca
 *
 */
public class CognomeController implements Controller {

	/**
	 * Espressione regolare per un cognome valido.
	 */
	private String regex = "[a-zA-Z'\\s]{2,15}";
	
	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}
