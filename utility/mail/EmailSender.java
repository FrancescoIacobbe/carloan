package utility.mail;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.EmailException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

import config.ConfiguratorReader;
import config.ConfiguratorSMTPReader;

/**
 * Invia e-mail tramite server SMTP. Utilizza i parametri inseriti nel file di
 * configurazione "smtp.cfg".
 * 
 * @author Ziri
 * @version 1.0
 */
public class EmailSender {

	/**
	 * Path del file di configurazione del server SMPT
	 */
	private final static String PATH = "src/config/smtp.cfg";

	/**
	 * Istanzia un oggetto {@link ConfiguratorReader} per leggere i parametri di
	 * configurazione del server SMTP.
	 */
	private ConfiguratorReader reader = new ConfiguratorSMTPReader(PATH);

	/**
	 * Username per l'accesso al server SMTP.
	 */
	private String username = reader.getField(ConfiguratorSMTPReader.USERNAME);

	/**
	 * Password per l'accesso al server SMTP.
	 */
	private String password = reader.getField(ConfiguratorSMTPReader.PASSWORD);

	/**
	 * Indirizzo server SMTP.
	 */
	private String host = reader.getField(ConfiguratorSMTPReader.HOST);

	/**
	 * Porta server SMTP.
	 */
	private String port = reader.getField(ConfiguratorSMTPReader.PORT);

	/**
	 * Accesso con autenticazione se true.
	 */
	private final boolean auth = true;

	/**
	 * Oggetto della mail.
	 */
	private String object;

	/**
	 * Contenuto della mail.
	 */
	private String message;

	private ArrayList<HashMap<String, String>> recipients;

	private int counter;

	/**
	 * Costruttore della classe EmailSender.
	 * 
	 * @param o
	 *            - oggetto della mail.
	 * @param mess
	 *            - contenuto della mail.
	 * @param dest
	 * 			  - lista di dizionari con i destinatari delle email.
	 * 				Ciascun dizionario è costituito dalle chiavi email e nome.
	 */
	public EmailSender(String o, String mess,
			ArrayList<HashMap<String, String>> dest) {
		this.object = o;
		this.message = mess;
		this.recipients = dest;
		this.counter = 0;
	}

	/**
	 * Restituisce il numero dei destinatari della mail.
	 * 
	 * @return il numero dei destinatari.
	 */
	public int getSize() {
		return this.recipients.size();
	}

	/**
	 * Restituisce il numero di email inviate in un istante.
	 * 
	 * @return il numero si email inviate in un istante.
	 */
	public int getCounter() {
		return this.counter;
	}

	/**
	 * Indirizzo email del destinatario corrente.
	 * 
	 * @return email del destinatario corrente.
	 */
	public String getCourrentEmail() {
		return this.recipients.get(this.counter).get("email");
	}

	/**
	 * Nome del destinatario corrente.
	 * 
	 * @return nome del destinatario corrente.
	 */
	public String getCourrentName() {
		return this.recipients.get(this.counter).get("nome");
	}

	/**
	 * Invia la email ad uno specifico destinatario.
	 * @return true sse l'invio è andato a buon fine, altrimenti false.
	 */
	public boolean send() {

		boolean inviato = false;
		if (counter < this.recipients.size()) {
			Properties props = new Properties();

			props.put("mail.smtp.host", host);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", port);

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			try {
				String email = this.getCourrentEmail();
				String nome = this.getCourrentName();
				Message mail = new MimeMessage(session);
				mail.setFrom(new InternetAddress(username));
				InternetAddress address = new InternetAddress(email);
				mail.setRecipient(Message.RecipientType.TO, address);
				mail.setSubject(object);
				String pers = "Gentile " + nome + ", \n";
				mail.setText(pers + message);
				Transport.send(mail);
				inviato = true;
			} catch (Exception e) {
				inviato = false;
				EmailException ex = new EmailException(e.getMessage());
				GestioneErroriInt ge = GestioneErrori.getIstance();
				ge.submitError(ex.getClass(), ex, GestioneErroriInt.NO_MESSAGE);
			} finally {
				counter++;
			}
		}
		return inviato;
	}

	/**
	 * Invia le email a tutti i destinatari.
	 * 
	 * @return true sse il processo termina con successo.
	 */
	public boolean sendAll() {
		int size = this.recipients.size();
		for (int i = 0; i < size; i++) {
			send();
		}
		return auth;
	}
}
