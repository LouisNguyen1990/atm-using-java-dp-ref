package atm.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import atm.utils.ATMUtils;

public abstract class Screen extends JPanel {	
	public static final String WELCOME = "Welcome";
	public static final String LOGIN_MENU = "Login";
	public static final String MAIN_MENU = "Main Menu";
	public static final String VIEW_BALANCE = "View Balance";
	public static final String WITHDRAW_MENU = "Withdraw";
	public static final String CHANGE_PIN = "Change Pin";
	public static final String TRANSFER1 = "Transfer1";
	public static final String TRANSFER2 = "Transfer2";	
	
	private String imageFolder = "images";
	private Image bgImage;	

	public Screen() {
		initComponents();
	}

	private void initComponents() { //template method
		configBackgroundImage();
		configSize();
		addComponents();
	}
	
	/**
	 * Form template methods..
	 */
	protected void configBackgroundImage() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), imageFolder + "/" + 
				getImageName(), getImageDescription()).getImage();
	}
	
	protected void configSize() {
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}

	abstract protected void addComponents();
	abstract protected String getImageName();
	abstract protected String getImageDescription();
	
	/**
	 * Print & display methods
	 */
	abstract public void printMessage(String msg, int pos);
	
	abstract public void displayInput(String msg);
	
	public void clearDisplay() {
		displayInput("");
	}
	
	/**
	 * Factory methods..
	 */
	public static Screen getBalanceInquiryScreen() {
		return new BalanceInquiryScreen();
	}
	
	public static Screen getChangePINScreen() {
		return new ChangePINScreen();
	}
	
	public static Screen getLoginScreen() {
		return new LoginScreen();
	}
	
	public static Screen getMainMenuScreen() {
		return new MainMenuScreen();
	}
	
	public static Screen getTransferScreen1() {
		return new TransferScreen1();
	}
	
	public static Screen getTransferScreen2() {
		return new TransferScreen2();
	}
	
	public static Screen getWelcomeScreen() {
		return new WelcomeScreen();
	}
	
	public static Screen getWithdrawScreen() {
		return new WithdrawScreen();
	}
	
	/** 
	 * Override paintComponent(g)
	 */
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}

}