package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue un controllo sulla città.
 * Il nome della città deve essere costituito da lettere e 
 * deve avere una lunghezza compresa tra 2 e 40 caratteri.
 * @author Gianluca
 *
 */
public class CittaController implements Controller {
	
	/**
	 * Espressione regolare per una città valida.
	 */
	private String regex = "[a-zA-z'.\\s]{2,40}";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}