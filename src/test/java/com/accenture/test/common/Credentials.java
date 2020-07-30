package com.accenture.test.common;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Credentials {

	public String typeUser() {
		String user = "";
		JTextField tf = new JTextField();
		int okCxl = JOptionPane.showConfirmDialog(null, tf, "Please type your user id :", JOptionPane.OK_OPTION);
		if (okCxl == JOptionPane.OK_OPTION) {
			user = new String(tf.getText());
		} else {
			System.exit(1);
		}

		return (user);

	}

	public String typePassword() {
		String password = "";
		JPasswordField pf = new JPasswordField();
		int okCxl = JOptionPane.showConfirmDialog(null, pf, "Please type your password :", JOptionPane.OK_OPTION);
		if (okCxl == JOptionPane.OK_OPTION) {
			password = new String(pf.getPassword());
		}else {
			System.exit(1);
		}
		return (password);
	}

	public String capturePasswordConfirmation() {
		String password = "";
		JPasswordField pf = new JPasswordField();
		int okCxl = JOptionPane.showConfirmDialog(null, pf, "Please type your password :", JOptionPane.OK_OPTION);
		if (okCxl == JOptionPane.OK_OPTION) {
			password = new String(pf.getPassword());
		}else {
			System.exit(1);
		}
		return (password);
    }
	
	public String typeTimeSheetPeriod() {
		String period = "";
		JTextField tf = new JTextField();
		int okCxl = JOptionPane.showConfirmDialog(null, tf, "Please provide Timesheet period (mm/dd/yyyy) :", JOptionPane.OK_OPTION);
		if (okCxl == JOptionPane.OK_OPTION) {
			period = new String(tf.getText());
			period = period.trim();
			if(period.charAt(0) == '0') {
				period = period.substring(1);
			}
		} else {
			System.exit(1);
		}

		return period;

	}

	public String selectTab() {
		String environment = "";
		JTextField textField = new JTextField(10);
		JPanel panel = new JPanel();
		JTextField tf = new JTextField();
		String[] envOptions = { "Review", "Represent", "Approve" };
		JComboBox envListCombo = new JComboBox(envOptions);

		envListCombo.setSelectedIndex(0);

		int envSelected = JOptionPane.showConfirmDialog(null, envListCombo, "Select the tab", JOptionPane.OK_OPTION);
		envSelected = envListCombo.getSelectedIndex();
		switch (envSelected) {
		case 0:
			environment = "review";
			break;
		case 1:			
			environment = "represent";
			break;
		case 2:			
			environment = "approve";
		}
				
		return (environment);
	}

}