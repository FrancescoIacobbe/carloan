package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue un controllo sul codice fiscale.
 * @author Gianluca
 *
 */
public class CFController implements Controller {
	
	/**
	 * Espressione regolare per un corretto codice fiscale.
	 */
	private String regex = "[a-zA-Z]{16}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}
