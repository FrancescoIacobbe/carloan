package utility.controller;

/**
 * Verifica che una stringa sia un {@link Double}.
 * @author Gianluca
 *
 */
public class ValutaController implements Controller{

	@Override
	public boolean check(String string) {
		// TODO Auto-generated method stub
		boolean controll;
		try {
			Double.parseDouble(string);
			controll = true;
		} catch (NumberFormatException e) {
			controll = false;
		}
		return controll;
	}

}
