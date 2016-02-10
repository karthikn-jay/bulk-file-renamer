package com.karthiknjay.bulkfilerenamer.util;


import javax.swing.JOptionPane;

public class SwingUtils {
	
	public static void showMessage(String message) {
		showMessage(message, Constants.APP_TITLE);
	}
	
	public static void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(
				null,
				message,
				title,
				JOptionPane.INFORMATION_MESSAGE);
	}

}
