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

public class RequisitoModel {
 // sistemare num
	public static final int Requisito = 4;
	private SimpleStringProperty ID;
	private SimpleStringProperty descrizione;
	private FrontControllerInt fc;
	private SimpleObjectProperty<Hyperlink> bottone;
	
	/**
	 * Costruttore principale.
	 * 
	 * @param id
	 *            l'id del requisito
	 * @param descrizione
	 *            la descrizione
	 */
	public RequisitoModel(String ID, String descrizione) {
		this.ID = new SimpleStringProperty(ID);
		this.descrizione= new SimpleStringProperty(descrizione);
		this.bottone= new SimpleObjectProperty<Hyperlink>(new Hyperlink());
		setButton( "riepilogoRequisito");
	}

	private void setButton(final String richiesta) {
		this.bottone.get().setCursor(Cursor.HAND);
		this.bottone.get().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				fc = new FrontController();
				ArrayList<String> p = new ArrayList<String>(1);
				p.addAll((Collection<? extends String>) ID);
				fc.processRequest(richiesta, p);
			}
		});
		
	}

	/**
	 * Restituisce la ID
	 * 
	 * @return la ID della requisito.
	 */
	public String getID() {
		return ID.get();
	}

	/**
	 * Restituisce la disponibilità.
	 * 
	 * @return la disponibilità della requisito.
	 */
	
	public String getDisponibilità() {
		return descrizione.get();
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
		String[] result = { ID.get(), descrizione.get()};
		return result;
	}
}
