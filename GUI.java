package project;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame {
	private JLabel valueLabel = new JLabel("Veuillez entrer un lien dans la zone de texte de droite et appuyer sur l'un des boutons pour effectuer l'action associ�e.");
	private JTextField linkField = new JTextField(30);
	private JTextField analyseField = new JTextField(30);
	private JButton arboParcours = new JButton("Parcours d'arborescence");
	private JButton scan = new JButton("Scanner");
	private JButton save = new JButton("Sauvegarder");
	private JButton aide = new JButton("Aide");
	private JButton browse = new JButton("Browse");
	private JButton quitButton = new JButton("Quitter");
	private JPanel rightPanel = new JPanel();
	
	/**
	 * This is the model in MVC (model-view-controller).
	 */

	public GUI(String title) {
		super(title);

		init();

		arboParcours.addActionListener(new ArboParcoursAction());
		scan.addActionListener(new ScanAction());
		save.addActionListener(new SaveAction());
		quitButton.addActionListener(new QuitAction(this)); 
		
	}

	private void init() {
		BorderLayout border = new BorderLayout();
		Container contentPane = getContentPane();
		contentPane.setLayout(border);
		
		rightPanel.setLayout(new GridLayout(5, 1));
		rightPanel.add(arboParcours);
		rightPanel.add(scan);
		rightPanel.add(save);
		rightPanel.add(aide);
		rightPanel.add(browse);
		contentPane.add(BorderLayout.EAST, rightPanel);
		contentPane.add(BorderLayout.NORTH, valueLabel);
		contentPane.add(BorderLayout.WEST, linkField);
		contentPane.add(BorderLayout.CENTER, analyseField);
		contentPane.add(BorderLayout.SOUTH, quitButton);
		
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private class ArboParcoursAction implements ActionListener {

		String lien = null;
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
		System.out.println("Saississez le lien");
		lien = linkField.getText();
		FilesWalk f = new FilesWalk(lien);	
		analyseField.setText(String.valueOf(f.toString()));
		}
	}

	 private class ScanAction implements ActionListener {

		String lien = null;
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Saississez le lien");
 			lien = linkField.getText();
 			Informations i = new Informations(lien);
 			System.out.println(i);
				Database d = new Database(i.getFileExt());
				try {
				Compare c = new Compare(i.getMimeType(),d.researchMime(),i.getSign(),d.researchSign());
				System.out.println(c);
				} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
		}

	}

	private class SaveAction implements ActionListener {

		String lien = null;
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Saississez le lien");
 			lien = linkField.getText();;
 			Informations i = new Informations(lien);
 			System.out.println(i);
				Database d = new Database(i.getFileExt());
				try {
				Compare c = new Compare(i.getMimeType(),d.researchMime(),i.getSign(),d.researchSign());
				System.out.println(c);
				Saving s = new Saving(i, c);
				Serialization ser = new Serialization(s);
				ser.serializationSave();
				System.out.println("Sauvegarde effectu�e");
				} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
		}

	}

	private class QuitAction implements ActionListener {
		private JFrame window;

		public QuitAction(JFrame window) {
			this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
		}

	}  
	
	private class AideAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	private class BrowseAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	public static void main(String[] args) {
		new GUI("GUI");
	} 
	
}