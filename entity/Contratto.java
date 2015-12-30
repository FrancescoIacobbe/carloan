package entity;

import gestioneErrori.GestioneErrori;
import gestioneErrori.GestioneErroriInt;
import gestioneErrori.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

import dataAccess.entity.ContrattoDao;
import dataAccess.factory.DaoFactory;

/**
 * Fornisce i servizi relativi le competizioni.
 * 
 * @author Gianluca
 * 
 */
public class Contratto implements LContrattoInt, SContrattoInt {

	private ContrattoDao dao;

	/**
	 * Costruttore principale.
	 * Istanzia il dao di riferimento.
	 */
	public Contratto() {
		try {
			this.dao = DaoFactory.getDaoFactory(DaoFactory.MYSQL)
					.getContrattoDao();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			DAOException ex = new DAOException(e.getMessage());
			GestioneErroriInt ge = GestioneErrori.getIstance();
			ge.submitError(ex.getClass(), ex, GestioneErroriInt.SEVERE);
		}
	}

	@Override
	public HashMap<String, String> getDatiContratto(String idContratto) {
		return dao.getDatiContratto(idContratto);
	}

	

	@Override
	public boolean compilaContratto(String idDipendente, String id, 
			String dataInizio, String dataLimite, String dataRestituzione,String luogoRestituzione,String tipoNoleggio, 
			 String tipoKm,String tipoFascia, double costoKm,int kmEffetivi,double acconto) {
		// TODO Auto-generated method stub
		return dao.compilaContratto(idDipendente, id, 
				dataInizio, dataLimite, dataRestituzione, luogoRestituzione, tipoNoleggio, 
				 tipoKm, tipoFascia, costoKm, kmEffetivi, acconto);
	}

	@Override
	public boolean setMacchinaAllocata(String idContratto,String macchina) {
		return dao.setMacchinaAllocata(idContratto, macchina);
	}

	
	@Override
	public boolean setDatiContratto(String id, String dataInizio,String dataLimite,String dataRestituzione,String luogoRestituzione, String tipoNoleggio,
			String tipoKm, String tipoFascia, double costoKm,int kmEffetivi, double acconto) {
		return dao.setDatiContratto(id, dataInizio, dataLimite, dataRestituzione, luogoRestituzione, tipoNoleggio, 
				 tipoKm, tipoFascia, costoKm, kmEffetivi, acconto);
	}

	@Override
	public boolean setTariffaBaseAllocato(String idContratto, double tariffaBase) {
		return dao.setTariffaBaseAllocato(idContratto, tariffaBase);
	}
	
	public boolean setPrezzoExtraAllocato(String idContratto,double prezzoExtra){
		return dao.setPrezzoExtraAllocato(idContratto, prezzoExtra);
	}
	public boolean setCostoTotAllocato(String idContratto,double costoTot){
		return dao.setCostoTotAllocato(idContratto, costoTot);
	}

	@Override
	public ArrayList<HashMap<String, String>> getContrattoSistema() {
		// TODO Auto-generated method stub
		return dao.getContrattoSistema();
	}

	

}
