package utility.controller;
import java.util.regex.Pattern;

/**
 * Esegue un controllo sul numero di telefono 
 * Il nome della città deve essere costituito da numeri e
 * deve avere una lunghezza di 10.
 * @author 
 *
 */
public class NumeroTelController implements Controller {

	/**
	 * Espressione regolare per un numero telefonico.
	 */
	private String regex = "[0-9/.\\s]{10}";
	
	@Override
	public boolean check(String string) {
    	return Pattern.matches(regex, string);
	}

}




	
	

	
