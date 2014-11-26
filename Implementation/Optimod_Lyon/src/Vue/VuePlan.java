package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.Vector;

import javax.swing.JPanel;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

public class VuePlan extends JPanel{

	private Vector<VueNoeud> listeVueNoeuds = null;
	private Vector<VueTroncon> listeVueTroncons = null;
	private int maxX = 0; 
	private int maxY = 0; 
	
	public VuePlan()
	{
		super();
	}
	
	public void chargerPlan(Plan planApp)
	{
		Vector<Noeud> listeNoeuds = planApp.getListeNoeuds();
		Vector<Troncon> listeTroncons = planApp.getListeTroncons();
		
		maxX = planApp.getMaxX(listeNoeuds);
		maxY = planApp.getMaxY(listeNoeuds);
		
		listeVueNoeuds = new Vector<VueNoeud>();
		listeVueTroncons = new Vector<VueTroncon>();

		for(Noeud a : listeNoeuds)
		{
			this.listeVueNoeuds.add(new VueNoeud(toScreenX(a.getX()), toScreenY(a.getY()), 10,a));
		}
		
		for(Troncon a : listeTroncons)
		{
			Noeud depart = a.getArrivee();
			Noeud arrivee = a.getDepart();
			VueNoeud vuedepart = new VueNoeud(toScreenX(depart.getX()), toScreenY(depart.getY()), 10, depart);
			VueNoeud vuearrivee = new VueNoeud(toScreenX(arrivee.getX()), toScreenY(arrivee.getY()), 10, arrivee); 
			
			VueTroncon b = new VueTroncon(vuedepart, vuearrivee);
			this.listeVueTroncons.add(b);
		}
	}
	
	public void paintComponent(Graphics g)
	{	    
	    if(listeVueNoeuds != null && listeVueTroncons != null)
	    {
	    	for(VueNoeud a : listeVueNoeuds)
	    	{
	    		a.dessiner(g);
	    	}
	    	g.setColor(new Color(5));
	    	for(VueTroncon a : listeVueTroncons)
	    	{
	    		a.dessiner(g);
	    	}
	    }
	}
	
	public int toScreenX(int x)
	{
		int width = this.getWidth();		
		float xecran = (float)(x) * (width-0) / (maxX + 30 - 0);
		return (int) xecran; 
	}
	public int toScreenY(int y)
	{
		int width = this.getHeight();		
		float yecran = (float)(y) * (width-0) / (maxY + 30 - 0);
		return (int) yecran; 
	}
	
	public Vector<VueNoeud> getListeVueNoeuds() {
		return listeVueNoeuds;
	}

	public Vector<VueTroncon> getListeVueTroncons() {
		return listeVueTroncons;
	}

}
