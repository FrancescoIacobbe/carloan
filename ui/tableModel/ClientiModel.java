package ui.tableModel;

import java.util.ArrayList;
import java.util.Collection;

import presentazione.FrontController;
import presentazione.FrontControllerInt;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;

public class ClientiModel {

	public static final int Clienti = 2;
	private SimpleStringProperty CF;
	private SimpleStringProperty cognome;
	private SimpleStringProperty nome;
	private FrontControllerInt fc;
	private SimpleObjectProperty<Hyperlink> bottone;
	
	/**
	 * Costruttore principale
	 * 
	 * @param CF
	 *            CF del cliente
	 * @param cognome
	 *            il cognome
	 * @param nome
	 *            il nome.
	 */
	public ClientiModel(String CF, String cognome, String nome, boolean sololettura) {
		this.CF = new SimpleStringProperty(CF);
		this.cognome= new SimpleStringProperty(cognome);
		this.nome= new SimpleStringProperty(nome);
		this.bottone= new SimpleObjectProperty<Hyperlink>(new Hyperlink());
		if(sololettura==false){
			setButton("riepiologoClienteDip");
		}
		
	}

	private void setButton(final String richiesta) {
		this.bottone.get().setCursor(Cursor.HAND);
		this.bottone.get().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				fc = new FrontController();
				ArrayList<String> p = new ArrayList<String>(1);
				p.addAll( (Collection<? extends String>) CF);
				fc.processRequest(richiesta, p);
			}
		});
		
	}

	/**
	 * Restituisce il CF
	 * 
	 * @return il codice fiscale del cliente.
	 */
	public String getCf() {
		return CF.get();
	}

	/**
	 * Restituisce il cognome.
	 * 
	 * @return il cognome del cliente.
	 */
	public String getCognome() {
		return cognome.get();
	}
	
	/**
	 * Restituisce il nome.
	 * 
	 * @return il nome del cliente.
	 */
	
	public String getNome() {
		return nome.get();
	}
	
	/**
	 * Restituisce l'hiperlink associato al bottone.
	 * 
	 * @return l'hyperlink associato al bottone.
	 */
	public Hyperlink getBottone() {
		return bottone.get();
	}

	/**
	 * Fornisce un array di stringhe contenenti le informazioni signficative del
	 * modello.
	 * 
	 * @return l'array con le informazioni.
	 */
	public String[] toArray() {
		String[] result = { CF.get(), cognome.get(), nome.get()};
		return result;
	}
}
