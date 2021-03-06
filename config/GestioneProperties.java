package config;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.GestionePropertiesException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Gestice i file di configurazione.
 * 
 * @author Gianluca
 * 
 */
public class GestioneProperties {

	private Properties properties;
	private FileInputStream in;
	private FileOutputStream out;
	private String path;

	/**
	 * Istanzia un oggetto GestioneProperies per il file specificato da path.
	 * 
	 * @param path
	 *            il percorso del file.
	 */
	public GestioneProperties(String path) {
		this.path = path;
		in = null;
		out = null;
		try {
			in = new FileInputStream(path);
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			GestionePropertiesException ex = new GestionePropertiesException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}

	/**
	 * Restituisce il valore associato ad una chiave.
	 * 
	 * @param key
	 *            la chiave.
	 * @return il valore associato alla chiave o null se la chiave non � presente.
	 */
	public String getValue(String key) {
		String result = null;
		if (properties.containsKey(key)) {
			result = properties.getProperty(key);
		}
		return result;
	}

	/**
	 * Inserisce un NUOVO attributo (chiave, valore).
	 * Se la chiave � gi� presente, viene richiamato il metodo {@code setValue}.
	 * 
	 * @param key
	 *            la chiave da inserire.
	 * @param value
	 *            il valore da associare a key.
	 */
	public void addValue(String key, String value) {
		if (properties.containsKey(key)) {
			setValue(key, value);
		}
		properties.put(key, value);
		try {
			out = new FileOutputStream(path);
			properties.store(out, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			GestionePropertiesException ex = new GestionePropertiesException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.WARNING);
		}
	}

	/**
	 * Imposta l'attributo value per la chiave key.
	 * 
	 * @param key la chiave.
	 * @param value il valore da impostare.
	 */
	public void setValue(String key, String value) {
		properties.setProperty(key, value);
		try {
			out = new FileOutputStream(path);
			properties.store(out, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			GestionePropertiesException ex = new GestionePropertiesException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.WARNING);
		}
	}
}
