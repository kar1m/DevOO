package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import Controleur.Application;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Plan;
import Modele.Troncon;

import java.awt.Color;
import java.util.Vector;

public class Fenetre extends JFrame {

	private JPanel contentPane;
	private VuePlan plan; 
	private JButton btnChargerPlan; 
	private JButton btnChargerDemandeLivraison;
	private JButton btnUndo; 
	private JButton btnRedo; 
	private JLabel lblChargementFini;
	/**
	 * Create the frame.
	 */
	public Fenetre() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 50, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		plan = new VuePlan();
		plan.setSize(new Dimension(100,230));
		contentPane.add(plan, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 100, 600);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		
		
		btnChargerPlan = new JButton("Charger Plan");
		panel_1.add(btnChargerPlan);
		
		btnChargerDemandeLivraison = new JButton("Charger demande livraison");
		btnChargerDemandeLivraison.setEnabled(false);
		panel_1.add(btnChargerDemandeLivraison);
		
		btnUndo = new JButton("undo");
		panel_1.add(btnUndo);
		
		btnRedo = new JButton("redo");
		panel_1.add(btnRedo);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		lblChargementFini = new JLabel("Bienvenue dans l'application");
		panel_2.add(lblChargementFini);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		JLabel lblPlan = new JLabel("Plan");
		panel_3.setBackground(new Color(2));
		panel_3.setSize(new Dimension(1000,5000));
		panel_3.add(lblPlan);
		
		
		setVisible(true);
	}

	
	

	public void logText(String t)
	{
		lblChargementFini.setText(t);
	}
	public void chargerPlan(Plan planApp)
	{
		plan.chargerPlan(planApp);
	}
	public void chargerLivraison(Vector<PlageHoraire> p)
	{
		plan.chargerLivraison(p);
	}



	public JPanel getContentPane() {
		return contentPane;
	}
	public VuePlan getPlan() {
		return plan;
	}
	public JButton getBtnChargerPlan() {
		return btnChargerPlan;
	}
	public JButton getBtnChargerDemandeLivraison() {
		return btnChargerDemandeLivraison;
	}
	public JButton getBtnUndo() {
		return btnUndo;
	}
	public JButton getBtnRedo() {
		return btnRedo;
	}	
	public JLabel getLblChargementFini() {
		return lblChargementFini;
	}
}
