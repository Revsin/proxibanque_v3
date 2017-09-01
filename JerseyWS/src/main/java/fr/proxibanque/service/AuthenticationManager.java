/**
 * 
 */
package fr.proxibanque.service;

import java.sql.SQLException;

import fr.proxibanque.dao.BankExecutiveDao;
import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.jdbc.BankExecutiveDaoImpl;
import fr.proxibanque.metier.BankExecutiveUser;

/**
 * @author SEBASTIENM
 * Classe d'authentification de login / pwd avec accès DAO
 * Utilisée par BankExecutiveUser (Conseiller)
 * Appelée par le WebService
 */
public class AuthenticationManager {

	public AuthenticationManager() {
		super();
	}

	public static boolean perform(BankExecutiveUser bankExec) {
		
		boolean authenticationOK = false;
		
		BankExecutiveDao bankExecDao = new BankExecutiveDaoImpl();
		
		// Bank Executive obtenu de la base de données grâce à son login
		BankExecutiveUser beu;
		try {
			beu = bankExecDao.searchByLogin(bankExec.getLogin());
			
			if(beu != null) {
				
				if(bankExec.getLogin().equals("") || bankExec.getPwd().equals("")) {
					
					System.err.println("Erreur WS: Login / Password vides!");
					return authenticationOK;
				}
				
				if(beu.getLogin().equals(bankExec.getLogin()) && beu.getPwd().equals(bankExec.getPwd())) {
							
					System.out.println("Authentification OK");
					return authenticationOK = true;
				} 			
			}
			
		} catch (DaoException e) {
			
			e.printStackTrace();
		} catch (DoesNotExistDaoException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return authenticationOK;
	}
}
