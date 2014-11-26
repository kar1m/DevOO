package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import Modele.Noeud;

public class VueNoeud {

	protected Noeud noeudAssocie;
	protected int x; 
	protected int y; 
	protected int rayon; 
	public boolean selected = false;
	
	public VueNoeud(int x, int y, int rayon, Noeud noeudAssocie)
	{
		this.x = x; 
		this.y = y; 
		this.rayon = rayon;
		this.noeudAssocie = noeudAssocie;
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
			g.setColor(Color.BLACK);
		}else
		{
			g.setColor(Color.BLACK);
		}
		g.fillOval(x, y, rayon, rayon);
		
	
	}
	
	
	
	public int getRayon() {
		return rayon;
	}
	public boolean clickDessus(int xclick, int yclick)
	{
		return xclick>x && xclick<x+rayon && yclick>y && yclick<y+rayon; 
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Noeud getNoeudAssocie() {
		return noeudAssocie;
	}
	
	
	
}
