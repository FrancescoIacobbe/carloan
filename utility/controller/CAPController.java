package utility.controller;
import java.util.regex.Pattern;

/**
 * Esegue un controllo sul CAP 
 * Il nome della città deve essere costituito da numeri e
 * deve avere una lunghezza di 5.
 * @author 
 *
 */
public class CAPController implements Controller {

	/**
	 * Espressione regolare per un numero telefonico.
	 */
	private String regex = "[0-9/.\\s]{5}";
	
	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}

}




	
	

	
