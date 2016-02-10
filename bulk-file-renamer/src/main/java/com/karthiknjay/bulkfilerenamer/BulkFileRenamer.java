package com.karthiknjay.bulkfilerenamer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.karthiknjay.bulkfilerenamer.util.Constants;
import com.karthiknjay.bulkfilerenamer.util.SwingUtils;

public class BulkFileRenamer {

	public static void main(String[] args) {
		BulkFileRenamer gui = new BulkFileRenamer();
		gui.showGUI();
	}
	
	private static String VERSION = "1.0.0";

	private JFrame jf;
	
	private JPanel pnlHeader;
	private JComponent pnlCenter;
	private JPanel pnlFooter;
	
	private JTextField txtBrowseFolder;
	private JButton btnBrowse;
	private JFileChooser fcBrowse;
	
	private JTextField txtFind;
	private JTextField txtReplace;
	private JButton btnFind;
	private JButton btnReplace;
	
	private JTextArea txtResult;
	
	private JLabel lblStatus;
	
	private void showGUI() {
		// Set client OS's look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		// build and show UI in a separate swing's thread
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					buildGUI(); // build the GUI
				}
			});
		} catch (Exception exc) {
			System.out.println(" Error while buildGUI : " + exc.getMessage());
			exc.printStackTrace();
		}
	}
	
	private void buildGUI() {
		try {
			jf = new JFrame(Constants.APP_TITLE);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.getContentPane().setLayout(new BorderLayout()); // set layout
			
			jf.getContentPane().add(getHeaderPanel(), BorderLayout.NORTH);
			
			pnlCenter = getCenterPanel();
			jf.getContentPane().add(pnlCenter, BorderLayout.CENTER);
			
			jf.getContentPane().add(getFooterPanel(), BorderLayout.SOUTH);
			
			jf.pack();
			jf.setSize(750, 450);
			jf.setLocation(200, 200);
			jf.setVisible(true);
			
		} catch(Exception e) {
			SwingUtils.showMessage("Error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Component getHeaderPanel() {
		pnlHeader = new JPanel();
		pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.LINE_AXIS));
		
		txtBrowseFolder = new JTextField("", 30);
		txtBrowseFolder.setEditable(false);
		
		btnBrowse = new JButton("Browse...");
		btnBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fcBrowse = new JFileChooser();
				fcBrowse.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int fcStatus = fcBrowse.showOpenDialog(jf);
				
				if( fcStatus == JFileChooser.APPROVE_OPTION) {
					String selectedFolder = fcBrowse.getSelectedFile().getAbsolutePath();
					txtBrowseFolder.setText(selectedFolder);
					System.out.println("selectedFolder : " + selectedFolder);
				}
			}
			
		});
		
		JPanel hPanel1 = new JPanel();
		hPanel1.setLayout(new FlowLayout(FlowLayout.LEADING));
		hPanel1.add(new JLabel(Constants.LBL_BROWSE));
		
		hPanel1.add(txtBrowseFolder);
		hPanel1.add(btnBrowse);
		
		txtFind = new JTextField("", 15);
		txtReplace = new JTextField("", 15);
		btnFind = new JButton("Find");
		btnReplace = new JButton("Replace");
		
		JPanel hPanel2 = new JPanel();
		hPanel2.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		hPanel2.add(new JLabel("Find: "));
		hPanel2.add(txtFind);
		hPanel2.add(new JLabel("Replace: "));
		hPanel2.add(txtReplace);
		hPanel2.add(btnFind);
		hPanel2.add(btnReplace);
		
		JPanel hPanel = new JPanel();
		hPanel.setLayout(new GridLayout(2, 0));
		hPanel.add(hPanel1);
		hPanel.add(hPanel2);
		
		pnlHeader.add(hPanel);
		return pnlHeader;
	}
	
	public JComponent getCenterPanel() {
		txtResult = new JTextArea();
		
		final JScrollPane scrollPane = new JScrollPane( txtResult );
		return scrollPane;
	}
	
	public Component getFooterPanel() {
		pnlFooter = new JPanel();
		lblStatus = new JLabel();
		
		pnlFooter.setLayout(new BoxLayout(pnlFooter, BoxLayout.LINE_AXIS));
		pnlFooter.add(lblStatus);
		pnlFooter.add(Box.createHorizontalGlue());
		pnlFooter.add(new JLabel("v" + VERSION));
		return pnlFooter;
	}

}
