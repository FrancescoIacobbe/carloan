package utility.mail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementa l'interfaccia {@link GestioneEmailInt}.
 * 
 * @author Gaetano
 * 
 */
public class GestioneEmail implements GestioneEmailInt {

	@Override
	public void visibleSend(String o, String mess,
			ArrayList<HashMap<String, String>> dest) {
		VisibleEmailSender.setParams(o, mess, dest);
		VisibleEmailSender.launch();
	}

	@Override
	public void backgroundSend(String o, String mess,
			ArrayList<HashMap<String, String>> dest) {
		BackgroundEmailSender sender = new BackgroundEmailSender(o, mess, dest);
		sender.start();
	}
}
