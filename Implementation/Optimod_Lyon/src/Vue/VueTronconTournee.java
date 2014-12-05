package Vue;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Outils.Proprietes;
/**
 * 
 * @author Mehdi Kitane
 *
 */
public class VueTronconTournee extends VueTroncon {
	private int couleur;

	public VueTronconTournee(VueNoeud noeud1, VueNoeud noeud2, int couleur) {
		super(noeud1, noeud2);
		this.couleur = couleur;  
	}

	/**
	 * Methode pour dessiner une vue
	 * @param g
	 */
	public void dessiner(Graphics g)
	{
        g.setColor( Proprietes.cols[couleur]);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(noeudDepart.getX(), noeudDepart.getY(), noeudArrivee.getX(), noeudArrivee.getY());
	
		float x1 = this.noeudArrivee.x;
		float y1 = this.noeudArrivee.y;
		
		float x2 = this.noeudDepart.x;
		float y2 = this.noeudDepart.y;
		
		
		
		int xArrow = (int) (x1 + (x2-x1)/4 );
		int yArrow = (int) (y1 + (y2-y1)/4 );
		
		int size = 15; 
		double phi = Math.atan2(y1-y2,x1-x2);
		double teta = Math.toRadians(30);
		double alpha = phi + teta; 
		double beta = phi - teta; 
		
		int x4 = (int) (xArrow - size*Math.cos(alpha)); 
		int y4 = (int) (yArrow - size*Math.sin(alpha));
		int x5 = (int) (xArrow - size*Math.cos(beta)); 
		int y5 = (int) (yArrow - size*Math.sin(beta));		
		
		g.drawLine(xArrow, yArrow, x4, y4 );
		g.drawLine(xArrow, yArrow, x5, y5 );
	}
 
}
