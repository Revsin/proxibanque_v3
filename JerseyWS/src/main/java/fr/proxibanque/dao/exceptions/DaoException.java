package fr.proxibanque.dao.exceptions;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DaoException() {
		super("Erreur : probleme d'acces à la base de donnees");
		
	}

	public DaoException(String message) {
		super(message);
	}

	
}
