package utility.controller;

/**
 * Interfaccia per eseguire un controllo su un campo o un valore.
 * @author Gianluca
 *
 */
public interface Controller {
	
	/**
	 * Esegue il controllo sul campo e\o sul valore string.
	 * @param string il campo e\o valore da controllare.
	 * @return true sse si tratta di un campo e\o valore valido, altrimenti false.
	 */
	public boolean check(String string);

}
