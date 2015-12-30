package utility;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.GestioneDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Converte stringhe in date e viceversa.
 * 
 * @author Gianluca
 * 
 */
public class GestioneDate {

	/**
	 * Pattern per la formattazione della data e del tempo nel tipo dd/mm/yyyy hh:mm:ss.
	 */
	public static final String DATATIME = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * Pattern per la formattazione della data e del tempo nel tipo dd/mm/yyyy hh:mm.
	 */
	public static final String DATATIME_MIN = "dd/MM/yyyy HH:mm";
	
	/**
	 * Pattern per la formattazione della data e del tempo nel tipo yyyy-mm-dd hh:mm:ss (anglosassone).
	 */
	public static final String DATATIME_ANGLO = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Pattern per la formattazione della data nel tipo dd/mm/yyyy.
	 */
	public static final String DATA = "dd/MM/yyyy";

	/**
	 * Pattern per la formattazione della data nel tipo yyyy-mm-dd (anglosassone).
	 */
	public static final String DATA_ANGLO = "yyyy-MM-dd";

	/**
	 * Data minima di sistema.
	 */
	public static final String MIN_DATE = "1900-01-01";

	private SimpleDateFormat sdf;

	/**
	 * Costruttore principale.
	 */
	public GestioneDate() {
		this.sdf = new SimpleDateFormat();
		sdf.setLenient(false);
	}

	/**
	 * Converte una stringa in un oggetto Calendar.
	 * 
	 * @param data
	 *            la data da convertire.
	 * @param format
	 *            il pattern di conversione da utilizzare.
	 * @return l'oggetto Calendar corrispondente o null se la conversione
	 *         fallisce.
	 */
	public Calendar parseString(String data, String format) {
		sdf.applyPattern(format);
		Calendar result = null;
		try {
			result = new GregorianCalendar();
			result.setTimeInMillis(sdf.parse(data).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			GestioneDateException ex = new GestioneDateException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.WARNING);
		}
		return result;
	}

	/**
	 * Converte un oggetto Calendar in una stringa.
	 * 
	 * @param data
	 *            l'oggetto da convertire.
	 * @param format
	 *            il pattern di conversione da utilizzare.
	 * @return la stringa convertita o null se la conversione fallisce.
	 */
	public String parseData(Calendar data, String format) {
		sdf.applyPattern(format);
		String result = null;
		result = sdf.format(data.getTime());
		return result;
	}
}
