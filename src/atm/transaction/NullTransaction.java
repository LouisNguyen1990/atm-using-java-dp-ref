package atm.transaction;

public class NullTransaction extends Transaction {
	@Override
	public void execute() {		
		System.out.println("Under construction...");
	}
}