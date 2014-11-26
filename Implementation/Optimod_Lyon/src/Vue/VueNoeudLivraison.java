package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import Modele.Noeud;
import Outils.Proprietes;

public class VueNoeudLivraison extends VueNoeud {
	private int couleur;
		   
	   
	public VueNoeudLivraison(int x, int y, int rayon, Noeud noeudAssocie, int couleur)
	{
		super(x,y,rayon,noeudAssocie);
		this.couleur = couleur;
		System.out.println(couleur);
	}
	public void dessiner(Graphics g)
	{
		if(selected)
		{			
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.BLACK);
			Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
	        g2d.setStroke(dashed);
	        g2d.drawOval(x-3, y-3, rayon+6, rayon+6);
	        g2d.dispose();

	        g.setColor( Proprietes.cols[couleur]);
		}else
		{
			g.setColor( Proprietes.cols[couleur]);
		}
		g.fillOval(x, y, rayon, rayon);
	}
}
