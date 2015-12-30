package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue un controllo sulla indirizzo.
 * Il nome della città deve essere costituito da lettere e numeri, 
 * deve avere una lunghezza compresa tra 2 e 40 caratteri.
 * @author Gianluca
 *
 */
public class IndirizzoController implements Controller {
	
	/**
	 * Espressione regolare per un indirizzo valido.
	 */
	private String regex = "[a-zA-Z0-9/.\\s]{2,40}";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}