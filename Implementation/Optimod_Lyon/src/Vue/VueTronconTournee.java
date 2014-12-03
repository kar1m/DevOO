package Vue;

import java.awt.Graphics;

import Outils.Proprietes;

public class VueTronconTournee extends VueTroncon {
	private int couleur;

	public VueTronconTournee(VueNoeud noeud1, VueNoeud noeud2, int couleur) {
		super(noeud1, noeud2);
		this.couleur = couleur;  
	}

	public void dessiner(Graphics g)
	{
        g.setColor( Proprietes.cols[couleur]);
		g.drawLine(noeudDepart.getX()+noeudDepart.getRayon()/2, noeudDepart.getY()+noeudDepart.getRayon()/2, noeudArrivee.getX()+noeudArrivee.getRayon()/2, noeudArrivee.getY()+noeudArrivee.getRayon()/2);
	}
}
