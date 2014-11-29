package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Modele.Client;
import Modele.Livraison;
import Modele.PlageHoraire;

public class AjoutLivraison extends JDialog {
	private PlageHoraire a; 
	private Client c; 
	private Livraison l; 
	  private boolean sendData;
	  private JLabel clientLabel,  horaireLabel;
	  private JComboBox PlageHoraire;
	  private JTextField Client;
	  
	  
	public AjoutLivraison(JFrame f, String titre, boolean modal)
	{
		super(f, titre, modal);
			    this.setSize(550, 270);
			    this.setLocationRelativeTo(null);
			    this.setResizable(false);
			    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			    this.initComponent();
		  }

		  private void initComponent(){
	

		    //Le Client
		    JPanel panNom = new JPanel();
		    panNom.setBackground(Color.white);
		    panNom.setPreferredSize(new Dimension(220, 60));
		    Client = new JTextField();
		    Client.setPreferredSize(new Dimension(100, 25));
		    panNom.setBorder(BorderFactory.createTitledBorder("Nom du personnage"));
		    clientLabel = new JLabel("Saisir un nom :");
		    panNom.add(clientLabel);
		    panNom.add(Client);



		 

		    //La couleur des cheveux
		    JPanel panHoraire = new JPanel();
		    panHoraire.setBackground(Color.white);
		    panHoraire.setPreferredSize(new Dimension(220, 60));
		    panHoraire.setBorder(BorderFactory.createTitledBorder("Plage Horaire"));
		    PlageHoraire = new JComboBox();
		    PlageHoraire.addItem("8h00-9h00");
		    PlageHoraire.addItem("9h00-10h00");
		    PlageHoraire.addItem("10h00-11h00");
		    PlageHoraire.addItem("11h00-12h00");
		    horaireLabel = new JLabel("Plage Horaire");
		    panHoraire.add(horaireLabel);
		    panHoraire.add(PlageHoraire);

		    JPanel content = new JPanel();
		    content.setBackground(Color.white);
		    content.add(panNom);
		    content.add(panHoraire);

		    JPanel control = new JPanel();
		    JButton okBouton = new JButton("OK");
		    
		    

		      
		    
		    };

		   
		  
		

	     
	}

