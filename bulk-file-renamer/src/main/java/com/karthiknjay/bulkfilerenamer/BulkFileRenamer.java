package com.karthiknjay.bulkfilerenamer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.karthiknjay.bulkfilerenamer.util.SwingUtils;

public class BulkFileRenamer {

	public static void main(String[] args) {
		BulkFileRenamer gui = new BulkFileRenamer();
		gui.showGUI();
	}
	
	private static String VERSION = "1.0.0.0";

	private JFrame jf;
	private JPanel pnlHeader;
	private JComponent pnlCenter;
	private JPanel pnlFooter;
	private JLabel lblStatus;
	private JButton btnDownload;
	private JTextField txtSaveFolder;
	private JTable table;
	private JButton btnTestConnection;
	private JButton btnErpDownload;
	
	private static final String LBL_FILES_NOT_FOUND = "No File(s) Found.";
	private static final String LBL_DOWNLOAD_INFO = "Click Upload to upload the file(s).";
	private static final String LBL_RETRIEVING_FILES = "Retrieving files. Please wait...";
	private static final String LBL_UPLOAD_SUCCESS = "File(s) added to the Upload scheduler.";
	
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
			jf = new JFrame("FrameDemo");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.getContentPane().setLayout(new BorderLayout()); // set layout
			
			// add all panels to main applet container
			jf.getContentPane().add(getHeaderPanel(), BorderLayout.NORTH);
			
			pnlCenter = getCenterPanel(new ArrayList());
			jf.getContentPane().add(pnlCenter, BorderLayout.CENTER);
			
			jf.getContentPane().add(getFooterPanel(), BorderLayout.SOUTH);
			
			jf.pack();
			jf.setSize(600, 450);
			jf.setLocation(200, 200);
			jf.setVisible(true);
			
		} catch(Exception e) {
			SwingUtils.showMessage("Error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Component getHeaderPanel() {
		pnlHeader = new JPanel();
		//headerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.LINE_AXIS));
		
		txtSaveFolder = new JTextField();
		txtSaveFolder.setText("");
		
		JPanel pnlFilePath = new JPanel();
		pnlFilePath.setLayout(new FlowLayout(FlowLayout.LEADING));
		pnlFilePath.add(new JLabel("ERP Config folder : " ));
		
		JTextField txtFilePath = new JTextField( "", 30 );
		txtFilePath.setEditable(false);
		pnlFilePath.add(txtFilePath);
		
		pnlHeader.add(pnlFilePath);
		pnlHeader.add(Box.createHorizontalGlue());
		pnlHeader.add(new JLabel("v" + VERSION));
		
		return pnlHeader;
	}
	
	public JComponent getCenterPanel(List filesList) {
		JPanel panel = new JPanel();
		if(filesList == null || filesList.size() == 0) {
			panel.add(new JLabel("No File(s) found."), BorderLayout.CENTER);
			panel.revalidate();
			panel.repaint();
			return panel;
		} else {
			
			/*tableModel = new FilesListTableModel();
			tableModel.setModel(filesList);*/
			
			table = new JTable();
	        table.setPreferredScrollableViewportSize(new Dimension(600,200));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoResizeMode(JTable.WIDTH);
			
			final JScrollPane scrollPane = new JScrollPane( table );
			return scrollPane;
		}
	}
	
	public Component getFooterPanel() {
		// Footer button and status panel
		pnlFooter = new JPanel();
		lblStatus = new JLabel(LBL_DOWNLOAD_INFO);
		//lblStatus.setVisible(false);
		btnDownload = new JButton("Upload");
		
		/*if(filesList == null || filesList.size() == 0) {
			lblStatus.setText(LBL_FILES_NOT_FOUND);
			btnDownload.setEnabled(false);
		}*/
		
		/*btnDownload.setActionCommand(ButtonActions.ERP_IMPORT.name());
		btnDownload.addActionListener(this);*/
		
		pnlFooter.setLayout(new BoxLayout(pnlFooter, BoxLayout.LINE_AXIS));
		pnlFooter.add(lblStatus);
		pnlFooter.add(Box.createHorizontalGlue());
		pnlFooter.add(btnDownload);
		return pnlFooter;
	}

}
