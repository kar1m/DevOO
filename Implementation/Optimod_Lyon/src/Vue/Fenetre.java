package Vue;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	public Fenetre()
	{
		this.setVisible(true);
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel panneauBoutons = new JPanel();
		JButton boutonCharger = new JButton("Charger Plan");
		JButton boutonChargerXml = new JButton("Charger DemandeLivraison");
		panneauBoutons.add(boutonCharger);
		panneauBoutons.add(boutonChargerXml);
		
		this.setLayout(new BorderLayout());
		this.add(panneauBoutons, BorderLayout.NORTH);

	}
}
