package fr.proxibanque.json.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.proxibanque.metier.BankExecutiveUser;
import fr.proxibanque.service.AuthenticationManager;

/**
 * @author SMALBRANCHE
 * Classe Web Service JSON avec méthode GET
 * pour authentifier un Conseiller Proxibanque
 * en interrogeant la BDD mySQL
 */
@Path("/json/bankexec")
public class JSONService {


	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public BankExecutiveUser getBankExecInJSONwithGET() {

		BankExecutiveUser bankExec = new BankExecutiveUser();
		bankExec.setSurName("Gates");
		bankExec.setFirstName("Bill");

		return bankExec;

	}

	
	/**
	 * @author SMALBRANCHE
	 * Méthode d'authentification du Conseiller Proxibanque
	 * via interrogation de la BDD mySQL basée sur son login
	 * @param BankExecutiveUser : Conseiller de la banque avec login / pwd valides et en BDD
	 */	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces({MediaType.TEXT_PLAIN})
	public Status authenticateBankExecInJSONwithPOST(String bankExec) {
	
		System.out.println("WS POST: authenticateBankExecInJSONwithPOST");
		
		if(bankExec == null) {
					
			System.err.println("Erreur WS: Pas de Conseiller fourni!");
			return Response.Status.BAD_REQUEST;
		}
					
		BankExecutiveUser beu = new BankExecutiveUser();
		beu.setLogin("moiGrandConseiller");
		beu.setPwd("123Soleil");
		
		//"BankExecutiveUser [login=" + login + ", pwd=" + pwd + ", surName=" + surName + ", firstName="
		//+ firstName + ", exec_ID=" + exec_ID + "]		
		
		boolean authenticationResultOK = AuthenticationManager.perform(beu);
		
		if(authenticationResultOK == true) {
			String result = "BankExecutiveUser authentifié: " + bankExec;	
			System.out.println(result);
			return Response.Status.ACCEPTED;
		} else {
			String result = "Echec de l'authentification du BankExecutiveUser: " + bankExec;
			System.out.println(result);
			return Response.Status.UNAUTHORIZED;						
		}
	}
	
}