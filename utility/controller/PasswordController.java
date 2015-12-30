package utility.controller;

import java.util.regex.Pattern;

public class PasswordController implements Controller{
	/**
	 * Espressione regolare per una password valida.
	 */
	private String regex = "[a-zA-Z0-9\\s_.-]{8,15}";

	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
}
}