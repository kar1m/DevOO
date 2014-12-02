package Vue;

import java.awt.Graphics;


public class VueTroncon {
	protected VueNoeud noeudDepart;
	protected VueNoeud noeudArrivee;

	
	public VueTroncon(VueNoeud noeud1, VueNoeud noeud2)
	{
		this.noeudDepart = noeud1; 
		this.noeudArrivee = noeud2; 
	}
	public void dessiner(Graphics g)
	{
		g.drawLine(noeudDepart.getX()+noeudDepart.getRayon()/2, noeudDepart.getY()+noeudDepart.getRayon()/2, noeudArrivee.getX()+noeudArrivee.getRayon()/2, noeudArrivee.getY()+noeudArrivee.getRayon()/2);
	}
	
	public boolean clickDessus(int xclick, int yclick)
	{
		return false;
	}
}
