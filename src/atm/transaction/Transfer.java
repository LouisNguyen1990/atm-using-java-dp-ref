package atm.transaction;

import atm.bank.BankDatabase;
import atm.bean.Account;
import atm.input.Keypad;
import atm.screen.Screen;
import atm.screen.TransferScreen;

public class Transfer extends Transaction {
	
	private static final int CANCELLED = -1;
	
	/** Transfer constructor **/
	public Transfer(int userAccountNumber, Screen atmScreen, 
						BankDatabase atmBankDatabase, Keypad atmKeypad) {
		
		super(userAccountNumber, atmScreen, atmBankDatabase, atmKeypad);
	}

	/** performs the transaction **/
	public void execute()
	{
		double availableBalance; // amount available for transfer
		
		getScreen().show(Screen.TRANSFER);
		
		getScreen().getTransferScreen().showPanel(TransferScreen.ACCOUNT_PANEL);
		int transferAccountNumber = getKeypad().readInput(Keypad.TRANSFER_MODE);
		getScreen().getTransferScreen().clearDisplay();

		if (transferAccountNumber == CANCELLED) {
			return;
		}
		
		String transferAccountName = getBankDatabase().getFullName(transferAccountNumber);
		
		if (transferAccountName == null) {
			System.out.println("Invalid account");
			return;
		}
		
		getScreen().getTransferScreen().showPanel(TransferScreen.MONEY_PANEL);
		getScreen().getTransferScreen().printAccountInfo(transferAccountNumber, transferAccountName);
		double transferAmount = getKeypad().readInput(Keypad.TRANSFER_MODE);
		getScreen().getTransferScreen().clearDisplay();
		
		if (transferAmount == CANCELLED) {
			return;
		}
		
		availableBalance = getBankDatabase().getAvailableBalance(getAccountNumber());
		
		if (transferAmount <= availableBalance) {
			getBankDatabase().transfer(getAccountNumber(), transferAccountNumber, transferAmount);
		
			System.out.println("Transfered " + transferAmount + " to account:" + transferAccountNumber);
		}
		else {
			System.out.println("not money enough for transfering..");
		}
	}
}
