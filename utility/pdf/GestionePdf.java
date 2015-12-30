package utility.pdf;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.PdfException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Classe per la gestione di un file pdf.
 * 
 * @author Gianluca
 * 
 */
public class GestionePdf {

	/**
	 * Il file su cui effettuare l'elaborazione.
	 */
	private File file;

	/**
	 * Il documento associato al file.
	 */
	private Document documento;

	/**
	 * L'array contenente la dimensione delle celle.
	 */
	private int[] width;

	/**
	 * La grandezza standard delle celle.
	 */
	public static final int STANDARD_CELL_WIDTH = 50;

	/**
	 * Costruttore principale della classe.
	 * 
	 * @param f
	 *            il file su cui lavorare.
	 */
	public GestionePdf(File f) {

		this.file = f;
		this.documento = new Document(PageSize.A4);
		documento.addAuthor("Agroludos");
	}

	/**
	 * Verifica che le colonne dell'intestazione siano lo stesso numero delle
	 * colonne della tabella.
	 * 
	 * @param intestazione
	 *            l'intestazione.
	 * @param tabella
	 *            la tabella.
	 * @throws FormatoTabellaExcepion
	 */
	private void checkColumns(String[] intestazione, String[][] tabella)
			throws FormatoTabellaExcepion {
		for (int i = 0; i < tabella.length; i++) {
			if (intestazione.length != tabella[i].length) {
				throw new FormatoTabellaExcepion(
						"Lunghezza intestazione e colonne tabella non coincidono");
			}
		}
	}

	/**
	 * Realizza una nuova {@link PdfPTable}.
	 * @param intestazione l'intestazione della tabella.
	 * @param tabella la tabella.
	 * @return la {@link PdfPTable} istanziata.
	 */
	private PdfPTable makeTable(String[] intestazione, String[][] tabella) {
		PdfPTable tabellaPdf = new PdfPTable(intestazione.length);
		width = new int[intestazione.length];
		for (int i = 0; i < width.length; i++) {
			width[i] = STANDARD_CELL_WIDTH;
		}
		try {
			tabellaPdf.setWidths(width);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			gestisciEccezione(e);
		}

		Font fontIntestazione = new Font(Font.FontFamily.TIMES_ROMAN, 16,
				Font.BOLD, BaseColor.RED);
		Font fontContenuto = new Font(Font.FontFamily.TIMES_ROMAN, 12);

		PdfPCell cella = null;
		for (String x : intestazione) {
			cella = new PdfPCell(new Paragraph(x, fontIntestazione));
			cella.setBackgroundColor(new BaseColor(152, 255, 152));
			cella.setColspan(1);
			tabellaPdf.addCell(cella);
		}

		for (int i = 0; i < tabella.length; i++) {
			for (int j = 0; j < tabella[i].length; j++) {
				cella = new PdfPCell(new Paragraph(tabella[i][j],
						fontContenuto));
				cella.setBackgroundColor(new BaseColor(218, 253, 218));
				cella.setColspan(1);
				tabellaPdf.addCell(cella);
			}
		}
		return tabellaPdf;
	}

	/**
	 * Inserisce una tabella in un file pdf.
	 * @param intestazione l'intestazione della tabella.
	 * @param tabella la tabella.
	 */
	private void insertTabella(String[] intestazione, String[][] tabella) {
			try {
				checkColumns(intestazione, tabella);
				PdfPTable tabellaPdf = makeTable(intestazione, tabella);
				documento.add(tabellaPdf);
			} catch (DocumentException | FormatoTabellaExcepion  e) {
				gestisciEccezione(e);
			} 
	}
	
	/**
	 * Inserisce un'immagine in un file pdf.
	 * @param image l'immagine da inserire.
	 */
	private void insertImage(File image) {
		try {
			Image im = Image.getInstance(image.getAbsolutePath());
			documento.add(im);
		} catch(DocumentException | IOException e) {
			gestisciEccezione(e);
		} 
	}
	
	/**
	 * Realizza un report e lo memorizza in un file pdf.
	 * @param title il titolo del report.
	 * @param dati i dati da memorizzare.
	 * @param intestazione l'intestazione della tabella da memorizzare.
	 * @param tabella la tabella da memorizzare.
	 * @param image l'immagine da memorizzare.
	 * @return true sse il report è creato correttamente, altrimenti false.
	 */
	public boolean saveReport(String title, HashMap<String,String> dati, String[] intestazione, String[][] tabella, File image) {
		boolean controll = false;
		try {
			FileOutputStream out = new FileOutputStream(file);
			PdfWriter.getInstance(documento, out);
			documento.open();
			writeDati(title, dati);
			if (dati != null) {
				documento.newPage();
			} else {
				documento.add(Chunk.NEWLINE);
			}
			if (intestazione != null && tabella != null) {
				insertTabella(intestazione, tabella);
			}
			if (image != null) {
				insertImage(image);
			}
			documento.close();
			controll = true;
		} catch (DocumentException | FileNotFoundException | NullPointerException e) {
			gestisciEccezione(e);
		}
		return controll;
	}
	
	/**
	 * Aggiunge dati sotto forma di stringa al file pdf.
	 * @param title il titolo del documento.
	 * @param dati i dati da aggiungere.
	 */
	private void writeDati(String title, HashMap<String, String> dati) {
		try {
			if (title != null) {
				Paragraph p = new Paragraph(title, new Font(
						Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD,
						new BaseColor(255, 0, 0)));
				p.setAlignment(Element.ALIGN_TOP);
				documento.add(p);
			}
			if (dati != null) {
				Chunk c;
				for (Entry<String, String> e : dati.entrySet()) {
					String key = e.getKey();
					String value = e.getValue();
					c = new Chunk(key, new Font(FontFamily.TIMES_ROMAN, 14,
							Font.BOLD));
					documento.add(c);
					c = new Chunk(" ".concat(value));
					documento.add(c);
					documento.add(Chunk.NEWLINE);
				}
			}
		} catch (DocumentException e) {
			gestisciEccezione(e);
		}
	}
	
	/**
	 * Invia la richiesta al gestore delle eccezioni.
	 * @param e l'eccezione sollevata.
	 */
	private void gestisciEccezione(Exception e) {
		PdfException ex = new PdfException(e.getMessage());
		GestioneErroriInt ge = GestioneErrori.getIstance();
		ge.submitError(ex.getClass(), ex, GestioneErroriInt.WARNING);
	}
}
