package Vue;

import java.awt.Color;
import java.awt.Graphics;

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
			g.setColor(Color.BLUE);
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
