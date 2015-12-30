package utility.controller;

import java.util.regex.Pattern;

public class EmailController  implements Controller{
	/**
	 * Espressione regolare per una email valida.
	 */
	private String regex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}
	
}
