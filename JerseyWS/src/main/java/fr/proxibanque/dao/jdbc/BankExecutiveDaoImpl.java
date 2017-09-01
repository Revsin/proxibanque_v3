package fr.proxibanque.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.proxibanque.dao.exceptions.DaoException;
import fr.proxibanque.dao.exceptions.DoesNotExistDaoException;
import fr.proxibanque.dao.exceptions.DuplicatedDaoException;
import fr.proxibanque.metier.BankExecutiveUser;

import fr.proxibanque.dao.BankExecutiveDao;

public class BankExecutiveDaoImpl implements BankExecutiveDao {
		
	@Override
	public void create(BankExecutiveUser bankExec) throws DaoException, DuplicatedDaoException{
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "INSERT INTO bank_executive (surname, firstname, login, password) VALUES (?,?,?,?)";
			stmt = connection.prepareStatement(requete);
			stmt.setString(1, bankExec.getSurName());
			stmt.setString(2, bankExec.getFirstName());
			stmt.setString(3, bankExec.getLogin());
			stmt.setString(4, bankExec.getPwd());
			// 4 - Execution de la requete
			int nombre = stmt.executeUpdate();
			System.out.println("Result of Execute Update: " + nombre);
			
			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} catch(SQLIntegrityConstraintViolationException e) {
			
			throw new DuplicatedDaoException( "Erreur surname duplique -detail : " + e.getMessage() ) ;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}
	}

	@Override
	public void remove(int id) throws DaoException, DoesNotExistDaoException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int nombre =-1;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "DELETE FROM bank_executive WHERE exec_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			nombre = stmt.executeUpdate();
			
			if(nombre == 0)
				throw new DoesNotExistDaoException( "BankExecutiveUser inexistant");
			
			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (SQLException e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} 
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		}
		
		finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}		

	}

	@Override
	public List<BankExecutiveUser> searchAll() throws DaoException {
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=America/New_York";
			String login = "root";
			String password ="root";
			Connection connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete
			String requete = "select exec_id, surname, firstname, login, password from bank_executive";
			Statement stmt = connection.createStatement();
		
			// 4 - Execution de la requete
			ResultSet resultat = stmt.executeQuery(requete);
			// 5 - Traitement du resultat
			List<BankExecutiveUser> bankExecs = new ArrayList<>();
			
			while(resultat.next()) {
				BankExecutiveUser bankExec = new BankExecutiveUser();
				bankExec.setExec_ID(resultat.getInt("exec_id"));
				bankExec.setLogin(resultat.getString("login"));
				bankExec.setSurName(resultat.getString("surname"));
				bankExec.setFirstName(resultat.getString("firstname"));
				
				bankExecs.add(bankExec);
			}
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
			return bankExecs;
		
		}catch (Exception e) {		
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		}
	}

	@Override
	public BankExecutiveUser searchByLogin(String bankUserLogin) throws DaoException, DoesNotExistDaoException, SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankExecutiveUser bankExec = null;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "SELECT * FROM bank_executive WHERE login=?";
			stmt = connection.prepareStatement(requete);
			stmt.setString(1, bankUserLogin);
			// 4 - Execution de la requete
			rs = stmt.executeQuery();
			
			// 5 - Traitement du resultat
			
		
			
			if(rs.next() == true) {
				
				bankExec = new BankExecutiveUser();
															
				bankExec.setExec_ID(rs.getInt("exec_id"));					
				bankExec.setSurName(rs.getString("surname"));
				bankExec.setFirstName(rs.getString("firstname"));
				bankExec.setPwd(rs.getString("password"));
				bankExec.setLogin(rs.getString("login"));
				 				
			} else
				throw new DoesNotExistDaoException( "BankExecutiveUser inexistant");
				
			
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}		
			
		return bankExec;		
	}
	
	@Override
	public BankExecutiveUser searchById(int id) throws DaoException, DoesNotExistDaoException, SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BankExecutiveUser bankExec = null;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "SELECT * FROM bank_executive WHERE exec_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setInt(1, id);
			// 4 - Execution de la requete
			rs = stmt.executeQuery();
			
			// 5 - Traitement du resultat
			
		
			
			if(rs.next() == true) {
				
				bankExec = new BankExecutiveUser();
															
				bankExec.setExec_ID(rs.getInt("exec_id"));					
				bankExec.setSurName(rs.getString("surname"));
				bankExec.setFirstName(rs.getString("firstname"));
				bankExec.setPwd(rs.getString("password"));
				bankExec.setLogin(rs.getString("login"));
				 				
			} else
				throw new DoesNotExistDaoException( "BankExecutiveUser inexistant");
				
			
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}		
			
		return bankExec;
	}

	@Override
	public void update(BankExecutiveUser bankExec) throws DaoException, DoesNotExistDaoException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int nombre =-1;
		
		try {
			// 1 - Chargement de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2 - Creation d'une connection
			// localhost = 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/proxibanque?useSSL=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
			String login = "root";
			String password ="root";
			connection = DriverManager.getConnection(url, login, password);
			// 3 - preparation de la requete			
			String requete = "UPDATE bank_executive SET surname = ?, firstname = ?, login = ?, password = ?"
					+ "WHERE exec_id=?";
			stmt = connection.prepareStatement(requete);
			stmt.setString(1, bankExec.getSurName());
			stmt.setString(2, bankExec.getFirstName());
			stmt.setString(3, bankExec.getLogin());
			stmt.setString(4, bankExec.getPwd());
			stmt.setInt(5, bankExec.getExec_ID());
			// 4 - Execution de la requete
			nombre = stmt.executeUpdate();
			
			if(nombre == 0)
				throw new DoesNotExistDaoException( "BankExecutiveUser inexistant");
			
			// 5 - Traitement du resultat
			// 6 - Liberation des ressources
			stmt.close();
			connection.close();
		
		} 
		catch (Exception e) {
			//e.printStackTrace();
			throw new DaoException( "Erreur " + e.getMessage() ) ;
			
		} finally {
			
			if(stmt != null)			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
			
			if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DaoException( "Erreur " + e.getMessage() ) ;
			}
		}
	}

}
