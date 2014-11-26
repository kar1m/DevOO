package Vue;

import java.awt.Graphics;


public class VueTroncon {
	private VueNoeud noeud1;
	private VueNoeud noeud2;

	
	public VueTroncon(VueNoeud noeud1, VueNoeud noeud2)
	{
		this.noeud1 = noeud1; 
		this.noeud2 = noeud2; 
	}
	public void dessiner(Graphics g)
	{
		g.drawLine(noeud1.getX()+noeud1.getRayon()/2, noeud1.getY()+noeud1.getRayon()/2, noeud2.getX()+noeud2.getRayon()/2, noeud2.getY()+noeud2.getRayon()/2);
	}
	
	public boolean clickDessus(int xclick, int yclick)
	{
		return false;
	}
}
