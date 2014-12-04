package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import Modele.Livraison;
import Outils.Proprietes;

public class VueNoeudLivraison extends VueNoeud {
	private int couleur;
	private Livraison livraison;
	   
	   
	public VueNoeudLivraison(int x, int y, int rayon, Livraison l, int couleur)
	{
		super(x,y,rayon,l.getDestinataire().getNoeudAdresse());
		this.livraison = l ; 
		this.couleur = couleur;
	}
	public void dessiner(Graphics g)
	{
		if(selected)
		{			
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.BLACK);
			Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
	        g2d.setStroke(dashed);
	        g2d.drawOval(x-rayon-3, y-rayon-3, 2*rayon+6, 2*rayon+6);
	        g2d.dispose();

	        g.setColor( Proprietes.cols[couleur]);
		}else
		{
			g.setColor( Proprietes.cols[couleur]);
		}
		g.fillOval(x-rayon, y-rayon, 2*rayon, 2*rayon);
	}
	
	
	public Livraison getLivraison() {
		return livraison;
	}
}
