package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Modele.Client;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;

public class AjoutLivraison extends JDialog {
	private JComboBox plageHoraireComboBox;
	private JTextField clientField;
	private Vector<PlageHoraire> listePlage; 
	private Noeud noeudAdresse;
	private boolean btnOkSelected = false; 

	public AjoutLivraison(JFrame f, String title, boolean modal, Vector<PlageHoraire> p, Noeud noeudAdresse)
	{
		super(f, title, modal);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.listePlage=p;
		this.noeudAdresse = noeudAdresse;
		this.initComponent(p, noeudAdresse);
		this.setVisible(true);
	}

	private void initComponent(Vector<PlageHoraire> p, Noeud noeudAdresse){
		JPanel panNom = new JPanel();
		panNom.setPreferredSize(new Dimension(220, 60));
		clientField = new JTextField(); 
		clientField.setPreferredSize(new Dimension(100, 25));

		panNom.setBorder(BorderFactory.createTitledBorder("Client"));
		JLabel clientLabel = new JLabel("Saisir un id :");
		panNom.add(clientLabel);
		panNom.add(clientField);



		//La couleur des cheveux
		JPanel panHoraire = new JPanel();
		panHoraire.setPreferredSize(new Dimension(220, 80));
		panHoraire.setBorder(BorderFactory.createTitledBorder("Plage Horaire"));
		plageHoraireComboBox = new JComboBox();
		for(PlageHoraire a : p){
			plageHoraireComboBox.addItem(a.getHeureDebut() + "-" + a.getHeureFin());
		}
		panHoraire.add(plageHoraireComboBox);


		JPanel panAdresse = new JPanel();
		panAdresse.setPreferredSize(new Dimension(220, 80));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse"));
		JTextField adresse = new JTextField(); 
		adresse.setPreferredSize(new Dimension(100, 25));
		adresse.setText(Integer.toString(noeudAdresse.getIdNoeud()));
		adresse.setEnabled(false);
		panAdresse.add(adresse);

		JPanel content = new JPanel();
		content.add(panNom);
		content.add(panHoraire);
		content.add(panAdresse);


		JPanel panelBoutons = new JPanel();
		JButton btnOk = new JButton("Ok");
		JButton btnAnnuler = new JButton("Annuler");
		panelBoutons.add(btnOk);
		panelBoutons.add(btnAnnuler);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(panelBoutons, BorderLayout.SOUTH); 


		btnOk.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				btnOkSelected = true;
			}

		});
		btnAnnuler.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}

		});
	}


	public PlageHoraire getPlageSelectionnee()
	{
		return listePlage.get(plageHoraireComboBox.getSelectedIndex());
	}
	public int getIdClientSelectionne()
	{
		return Integer.parseInt(clientField.getText());
	}

	public boolean isBtnOkSelected() {
		return btnOkSelected;
	}



}

