package atm.gui.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atm.utils.ATMUtils;

public class ViewBalanceScreen extends JPanel {
	private JTextField accountNumberFld = new JTextField("123456");
	private JTextField fullNameFld = new JTextField("Vietcombank");
	private JTextField balanceFld = new JTextField("15.000.000 VND");
	private Image bgImage;
	
	public ViewBalanceScreen() {
		bgImage = ATMUtils.createImageIcon(this.getClass(), "images/viewbalance.png", "View balance").getImage();
		Dimension size = new Dimension(bgImage.getWidth(null), bgImage.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    
	    initComponents();
	}
	
	private void initComponents() {

		add(accountNumberFld);
		accountNumberFld.setBackground(Color.green);
		accountNumberFld.setBounds(165, 105, 140, 26);
		accountNumberFld.setForeground(Color.red);
		accountNumberFld.setEditable(false);
		
		add(fullNameFld);
		fullNameFld.setBackground(Color.green);
		fullNameFld.setBounds(165, 140, 140, 26);
		fullNameFld.setForeground(Color.red);
		fullNameFld.setEditable(false);
		
		add(balanceFld);
		balanceFld.setBackground(Color.green);
		balanceFld.setBounds(165, 175, 140, 26);
		balanceFld.setForeground(Color.red);
		balanceFld.setEditable(false);

	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(bgImage, 0, 0, null);
	}
}