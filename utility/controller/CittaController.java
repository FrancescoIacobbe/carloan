package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue un controllo sulla citt�.
 * Il nome della citt� deve essere costituito da lettere e 
 * deve avere una lunghezza compresa tra 2 e 40 caratteri.
 * @author Gianluca
 *
 */
public class CittaController implements Controller {
	
	/**
	 * Espressione regolare per una citt� valida.
	 */
	private String regex = "[a-zA-z'.\\s]{2,40}";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}