package fr.proxibanque.dao.exceptions;

public class DoesNotExistDaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DoesNotExistDaoException() {
		super("Erreur : client inexistant dans la base de donnees");
		
	}

	public DoesNotExistDaoException(String message) {
		super(message);
	}
}
