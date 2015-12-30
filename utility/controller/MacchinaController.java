package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue un controllo sulla targa della machhina.
 * @author Gianluca
 *
 */
public class MacchinaController implements Controller {
	
	/**
	 * Espressione regolare per un corretto targa macchina.
	 */
	private String regex = "[a-zA-Z]{7}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}
