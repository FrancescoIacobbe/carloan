package utility;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utility per la cifratura delle password.
 * La cifratura avviene attraverso algoritmo MD5.
 * Classe Singleton.
 * 
 * @author Gianluca
 *
 */
public class Security {
	
	private static MessageDigest md;
	private static Security security;
	private static final String SUFFIX = "carloan000";
	
	/**
	 * Costruttore privato.
	 */
	private Security() {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(e.getClass(), e, GestioneErrori.NO_MESSAGE);
		}
	}
	
	/**
	 * Fornisce l'unica istanza dell'oggetto security.
	 */
	private static void getIstance() {
		if (security == null) {
			security = new Security();
		}
	}
	
	/**
	 * Cifra la stringa nell'algoritmo di cifratura stabilito.
	 * @param s la stringa da cifrare.
	 * @return la stringa cifrata e convertita in una stringa esadecimale
	 * 			con lunghezza di 32 caratteri.
	 */
	public static String cipher (String s) {
		getIstance();
		byte[] temp;
		String result = null;
		try {
			temp = (s.concat(SUFFIX)).getBytes("UTF-8");
			byte[] code = md.digest(temp);
			StringBuilder sb = new StringBuilder(2*code.length);
			for (byte b : code) {
				sb.append(String.format("%02x", b&0xff));
			}
			result = sb.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(e.getClass(), e, GestioneErrori.NO_MESSAGE);
		}
		return result;
	}
}
