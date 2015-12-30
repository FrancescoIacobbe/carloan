package utility.controller;
import java.util.regex.Pattern;

/**
 * Esegue il controllo di un username.
 * La lunghezza del nome deve essere compresa tra 4 e 15 caratteri.
 * @author 
 *
 */
public class UsernameController implements Controller {

	private String regex = "[a-zA-Z0-9\\s_.-]{4,15}";
	@Override
	public boolean check(String string) {
		return Pattern.matches(regex, string);
	}

}
