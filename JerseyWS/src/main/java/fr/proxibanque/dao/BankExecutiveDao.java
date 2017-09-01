package fr.proxibanque.dao;

import java.sql.SQLException;
import java.util.List;

import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.metier.BankExecutiveUser;;

public interface BankExecutiveDao {

	public void  create(BankExecutiveUser client) throws DaoException, DuplicatedDaoException;
	public void  remove(int id) throws DaoException, DoesNotExistDaoException;
	public List<BankExecutiveUser> searchAll() throws DaoException;
	public BankExecutiveUser searchById(int id) throws DaoException, DoesNotExistDaoException, SQLException;
	public BankExecutiveUser searchByLogin(String login) throws DaoException, DoesNotExistDaoException, SQLException;
	public void  update(BankExecutiveUser client) throws DaoException, DoesNotExistDaoException;	
}

