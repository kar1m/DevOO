package Vue;

import java.awt.Graphics;

import Modele.Noeud;

public class VueNoeud {

	private Noeud noeudAssocie;
	private int x; 
	private int y; 
	private int rayon; 
	
	public VueNoeud(int x, int y, int rayon, Noeud noeudAssocie)
	{
		this.x = x; 
		this.y = y; 
		this.rayon = rayon;
		this.noeudAssocie = noeudAssocie;
	}
	public void dessiner(Graphics g)
	{
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
	
	
}
