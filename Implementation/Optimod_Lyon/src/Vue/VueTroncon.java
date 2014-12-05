package Vue;

import java.awt.Graphics;
/**
 * 
 * @author Mehdi Kitane
 *
 */

public class VueTroncon {
	protected VueNoeud noeudDepart;
	protected VueNoeud noeudArrivee;

	
	public VueTroncon(VueNoeud noeud1, VueNoeud noeud2)
	{
		this.noeudDepart = noeud1; 
		this.noeudArrivee = noeud2; 
	}
	/**
	 * Methode pour dessiner une vue
	 * @param g
	 */
	public void dessiner(Graphics g)
	{
		g.drawLine(noeudDepart.getX(), noeudDepart.getY(), noeudArrivee.getX(), noeudArrivee.getY());
	}
	
	/**
	 * Permet de savoir si on a clique sur cette vue
	 * @param xclick
	 * @param yclick
	 * @return boolean
	 */
	public boolean clickDessus(int xclick, int yclick)
	{
		return false;
	}
}
