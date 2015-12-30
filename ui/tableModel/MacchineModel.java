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

public class MacchineModel {

	public static final int Macchina = 1;
	private SimpleStringProperty targa;
	private SimpleStringProperty modello;
	private SimpleStringProperty disponibilita;
	private FrontControllerInt fc;
	private SimpleObjectProperty<Hyperlink> bottone;
	
	/**
	 * Costruttore principale.
	 * 
	 * @param idCont
	 *            l'id del contratto
	 * @param data
	 *            la data.
	 */
	public MacchineModel(String targa, String modello, String disponibilita, boolean sololettura) {
		this.targa = new SimpleStringProperty(targa);
		this.modello= new SimpleStringProperty(modello);
		this.disponibilita= new SimpleStringProperty(disponibilita);
		this.bottone= new SimpleObjectProperty<Hyperlink>(new Hyperlink());
		if(sololettura){
			setButton("riepiologoMacchineDip");
		}else{
			setButton( "riepiologoMacchineMng");
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
				p.addAll((Collection<? extends String>) targa);
				fc.processRequest(richiesta, p);
			}
		});
		
	}

	/**
	 * Restituisce la targa
	 * 
	 * @return la targa della macchina.
	 */
	public String gettarga() {
		return targa.get();
	}

	/**
	 * Restituisce il modello.
	 * 
	 * @return il modello della macchina.
	 */
	public String getModello() {
		return modello.get();
	}
	
	/**
	 * Restituisce la disponibilità.
	 * 
	 * @return la disponibilità della macchina.
	 */
	
	public String getDisponibilità() {
		return disponibilita.get();
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
		String[] result = { targa.get(), modello.get(), disponibilita.get()};
		return result;
	}
}
