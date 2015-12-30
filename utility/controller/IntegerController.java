package utility.controller;

public class IntegerController implements Controller {

	public boolean check(String string) {
		// TODO Auto-generated method stub
		boolean controll;
		try {
			Integer.parseInt(string);
			controll = true;
		} catch (NumberFormatException e) {
			controll = false;
		}
		return controll;
	}

}
