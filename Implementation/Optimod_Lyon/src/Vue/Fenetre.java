package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

import java.awt.Color;
import java.util.Vector;

public class Fenetre extends JFrame {

	private JPanel contentPane;
	private VuePlan plan; 

	/**
	 * Create the frame.
	 */
	public Fenetre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		plan = new VuePlan();
		plan.setBackground(Color.RED);
		contentPane.add(plan, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnChargerPlan = new JButton("Charger Plan");
		panel_1.add(btnChargerPlan);
		
		JButton btnChargerDemandeLivraison = new JButton("Charger demande livraison");
		panel_1.add(btnChargerDemandeLivraison);
		
		JButton btnUndo = new JButton("undo");
		panel_1.add(btnUndo);
		
		JButton btnRedo = new JButton("redo");
		panel_1.add(btnRedo);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblChargementFini = new JLabel("Chargement fini");
		panel_2.add(lblChargementFini);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		JLabel lblPlan = new JLabel("Plan");
		panel_3.add(lblPlan);
	}

	
	public void chargerPlan(Plan planApp)
	{
		plan.chargerPlan(planApp);
		System.out.println("planChargé");
	}
}
