package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import Controleur.Action;
import Controleur.Application;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Plan;
import Modele.Troncon;

import java.awt.Color;
import java.util.Vector;
/**
 * Vue principale de l'application
 * @author Mehdi Kitane
 *
 */
public class Fenetre extends JFrame {

	private JPanel contentPane;
	private VuePlan plan; 
	private JButton btnChargerPlan; 
	private JButton btnChargerDemandeLivraison;
	private JButton btnCalcul;
	private JButton btnUndo; 
	private JButton btnRedo; 
	private JButton btnEnregistrer; 
	private JLabel lblChargementFini;
	private VueTable table; 
	/**
	 * création de la fenetre
	 */
	public Fenetre() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 700);
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
		
		btnCalcul = new JButton("Calcul TournÃ©e");
		btnCalcul.setEnabled(false);
		panel_1.add(btnCalcul);
		
		btnUndo = new JButton("undo");
		btnUndo.setEnabled(false);
		panel_1.add(btnUndo);
		
		btnRedo = new JButton("redo");
		btnRedo.setEnabled(false);
		panel_1.add(btnRedo);
		
		btnEnregistrer = new JButton("Enregistrer Tournee");
		btnEnregistrer.setEnabled(false);
		panel_1.add(btnEnregistrer);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		lblChargementFini = new JLabel("Bienvenue dans l'application");
		panel_2.add(lblChargementFini);
		
		table = new VueTable();
		contentPane.add(table, BorderLayout.EAST);
		
		setResizable(false);
		
	}
	/**
	 * Mis à jour des boutons undo/redo
	 */
	public void updateUndoRedo(boolean undo, boolean redo)
	{
		btnUndo.setEnabled(undo);
		btnRedo.setEnabled(redo);
	}
	
	/**
	 * gestion du log dans la fenetre
	 */
	public void logText(String t)
	{
		lblChargementFini.setText(t);
	}
	public void chargerPlan(Plan planApp)
	{
		plan.chargerPlan(planApp);
	}
	public void chargerLivraison(Vector<PlageHoraire> p, Noeud entrepot)
	{
		plan.chargerLivraison(p, entrepot);
		table.chargerTable(p);
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
	public VueTable getTable() {
		return table;
	}
	public JButton getBtnCalcul() {
		return btnCalcul;
	}
	public JButton getBtnEnregistrer() {
		return btnEnregistrer;
	}
}
