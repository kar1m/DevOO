package Vue;

import java.awt.Graphics;

public class VueTronconTournee extends VueTroncon {

	public VueTronconTournee(VueNoeud noeud1, VueNoeud noeud2) {
		super(noeud1, noeud2);
	}

	public void dessiner(Graphics g)
	{
		g.drawLine(noeudDepart.getX()+noeudDepart.getRayon()/2, noeudDepart.getY()+noeudDepart.getRayon()/2, noeudArrivee.getX()+noeudArrivee.getRayon()/2, noeudArrivee.getY()+noeudArrivee.getRayon()/2);
	}
}
