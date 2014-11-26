package Vue;

import java.awt.Color;
import java.awt.Graphics;

import Modele.Noeud;

public class VueNoeudLivraison extends VueNoeud {
	public VueNoeudLivraison(int x, int y, int rayon, Noeud noeudAssocie)
	{
		super(x,y,rayon,noeudAssocie);
	}
	public void dessiner(Graphics g)
	{
		if(selected)
		{
			g.setColor(Color.BLUE);
		}else
		{
			g.setColor(Color.RED);
		}
		g.fillOval(x, y, rayon, rayon);
	}
}
