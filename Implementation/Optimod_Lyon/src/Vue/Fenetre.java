package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.*;

public class Fenetre extends JFrame {

	public Fenetre(Application master)
	{
		this.setVisible(true);
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel panneauBoutons = new JPanel();
		JButton boutonCharger = new JButton("Charger Plan");
		boutonCharger.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.chargerPlan();
				
			}
		});
		JButton boutonChargerXml = new JButton("Charger DemandeLivraison");
		boutonChargerXml.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.chargerDemandeLivraison();
				
			}
		});
		panneauBoutons.add(boutonCharger);
		panneauBoutons.add(boutonChargerXml);
		
		this.setLayout(new BorderLayout());
		this.add(panneauBoutons, BorderLayout.NORTH);

	}

}
