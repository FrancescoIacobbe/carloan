package utility.controller;

import java.util.regex.Pattern;

/**
 * Esegue il controllo di un nome.
 * La lunghezza del nome deve essere compresa tra 2 e 100 caratteri.
 * @author 
 *
 */
public class NomeController implements Controller {

	/**
	 * Espressione regolare per un nome valido.
	 */
	private String regex = "[a-zA-Z'\\s]{2,100}";
	
	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
}

