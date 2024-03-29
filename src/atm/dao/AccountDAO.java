package atm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import atm.bean.Account;
import atm.bean.TransactionVO;


public class AccountDAO extends DAO<Account> {
	//private static Connection connection = DBConnection.getInstance();
	
	public AccountDAO(Connection conn) {
		super(conn);
	}
	
	@Override
	public boolean insert(Account obj) {

		String sql = "INSERT INTO Account (accountNumber, fullName, pin, availableBalance) " +
					"VALUES (" + obj.getAccountNumber() + ", '" +
								obj.getFullName() + "', " +
								obj.getPIN() + ", " +
								obj.getAvailableBalance() +
							")";
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate(sql);
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean delete(Account obj) {
		return false;
	}
	
	@Override
	public boolean update(Account userAccount) {
		String sql = "UPDATE Account SET " +
					" availableBalance = " + userAccount.getAvailableBalance() +
					", pin = " + userAccount.getPIN() +
					" WHERE accountNumber = " + userAccount.getAccountNumber();

		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate(sql);
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();			
		}
		
		return false;
	}
	
	@Override
	public Account find(double theAccountNumber) {
		String sql = "SELECT * FROM Account, Transaction " +
					"WHERE Account.accountNumber = Transaction.accountNumber " + 
					"AND Account.accountNumber = " + theAccountNumber +
					" ORDER BY Transaction.date_time";
		
		Account account = Account.newNull(); // Refactoring > Introduce null object
		
		try {
			Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stm.executeQuery(sql);			
			
			if (!rs.first())
				return account;

			int accountNumber = rs.getInt("accountNumber");
			String fullName = rs.getString("fullName");
			int pin = rs.getInt("pin");
			double availableBalance = rs.getDouble("availableBalance");
			account = new Account(accountNumber, fullName, pin, availableBalance);
			
//			rs.beforeFirst();
//			DAO<Transaction> transactionDAO = DAOFactory.getTransactionDAO();
//
//			while (rs.next()) {
//				account.addTransaction(transactionDAO.find(rs.getInt("transaction_id")));
//			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return account;
	}
}
