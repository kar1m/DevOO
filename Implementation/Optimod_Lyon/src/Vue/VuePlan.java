package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import Modele.Chemin;
import Modele.Livraison;
import Modele.Noeud;
import Modele.PlageHoraire;
import Modele.Plan;
import Modele.Tournee;
import Modele.Troncon;

public class VuePlan extends JPanel{

	private Vector<VueNoeud> listeVueNoeuds = null;
	private Vector<VueTroncon> listeVueTroncons = null;
	private Vector<VueNoeudLivraison> listeVueNoeudLivraisons = null;
	private Vector<VueTronconTournee> listeTournee = null; 
	private VueNoeud vueEntrepot = null; 
	
	private int rayonNoeud = 5; 
	private int rayonEntrepot = 10; 
	
	private int maxX = 0; 
	private int maxY = 0; 
	
	public VuePlan()
	{
		super();
	}
	
	/**
	 * Methode qui charge le plan dans la vuePlan
	 * Cree les differentes vueNoeud et troncon correspondant
	 * @param planApp
	 */
	public void chargerPlan(Plan planApp)
	{
		listeVueNoeuds = new Vector<VueNoeud>();
		listeVueTroncons = new Vector<VueTroncon>();
		
		if(planApp!= null)
		{
			Vector<Noeud> listeNoeuds = planApp.getListeNoeuds();
			Vector<Troncon> listeTroncons = planApp.getListeTroncons();
			
			maxX = planApp.getMaxX(listeNoeuds);
			maxY = planApp.getMaxY(listeNoeuds);
			
			

			for(Noeud a : listeNoeuds)
			{
				this.listeVueNoeuds.add(new VueNoeud(toScreenX(a.getX()), toScreenY(a.getY()), rayonNoeud,a));
			}
			
			for(Troncon a : listeTroncons)
			{
				Noeud depart = a.getArrivee();
				Noeud arrivee = a.getDepart();
				VueNoeud vuedepart = new VueNoeud(toScreenX(depart.getX()), toScreenY(depart.getY()), rayonNoeud, depart);
				VueNoeud vuearrivee = new VueNoeud(toScreenX(arrivee.getX()), toScreenY(arrivee.getY()), rayonNoeud, arrivee); 
				
				VueTroncon b = new VueTroncon(vuedepart, vuearrivee);
				this.listeVueTroncons.add(b);
			}
		}
	}
	
	/**
	 * Methode qui charge les livraisons a partir du vecteur de plage horaire et de l'entrepot
	 * Cree les differentes vues noeud livraison correspondant
	 * @param p
	 * @param entrepot
	 */
	public void chargerLivraison(Vector<PlageHoraire> p, Noeud entrepot)
	{
		
		listeVueNoeudLivraisons = new Vector<VueNoeudLivraison>();
		vueEntrepot = null; 
		if(p != null)
		{
			for(PlageHoraire a : p)
			{
				for(Livraison l : a.getLivraisons()){
					Noeud noeud = l.getDestinataire().getNoeudAdresse();
					VueNoeudLivraison vueNoeud = new VueNoeudLivraison(toScreenX(noeud.getX()), toScreenY(noeud.getY()), rayonNoeud, l , a.getId());
					this.listeVueNoeudLivraisons.add(vueNoeud);
				}
			}	
		}
		if(entrepot != null)
		{
			vueEntrepot = new VueNoeud(toScreenX(entrepot.getX()), toScreenY(entrepot.getY()), rayonEntrepot, entrepot);
		}
	}
	/**
	 * Charge la tournée dans la vue, et l'affiche
	 * @param mapChemin : Map contenant les differents chemin en fonction de la plage horaire(Integer)
	 */
	public void chargerTournee(Tournee tournee, Vector<PlageHoraire> listePlageHoraires)
	{
		listeTournee = new Vector<VueTronconTournee>(); 
		
		if(tournee != null && listePlageHoraires != null)
		{
			Vector<Vector<Chemin>> mapChemin = tournee.getListeChemins();
			for(int i=0; i<mapChemin.size(); i++)
			{
				PlageHoraire plageHoraire = listePlageHoraires.get(i);
				Vector<Chemin> listeChemin = tournee.getListeChemins().get(i);
				
				for(Chemin chemin : listeChemin)
				{
					for(Troncon t : chemin.getListeTroncons())
					{
						Noeud depart = t.getDepart();
						Noeud arrivee = t.getArrivee();
						
						VueNoeud vuedepart = new VueNoeud(toScreenX(depart.getX()), toScreenY(depart.getY()), rayonNoeud, depart);
						VueNoeud vuearrivee = new VueNoeud(toScreenX(arrivee.getX()), toScreenY(arrivee.getY()), rayonNoeud, arrivee); 
						
						VueTronconTournee b = new VueTronconTournee(vuedepart, vuearrivee, plageHoraire.getId());
						this.listeTournee.add(b);
					}
				}
			}
		}
	}
	@Override
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
	    
	    if(listeVueNoeudLivraisons != null)
	    {
	    	for(VueNoeudLivraison a : listeVueNoeudLivraisons)
	    	{
	    		a.dessiner(g);
	    	}
	    }
	    
	    if(vueEntrepot != null)
	    {
	    	vueEntrepot.dessiner(g);
	    }
	    if(listeTournee != null)
	    {
	    	for(VueTronconTournee a : listeTournee)
	    	{
	    		a.dessiner(g);
	    	}
	    }
	}
	
	/**
	 * Converti un point X à des coordonnes ecran Xecran
	 * @param x
	 * @return Xecran 
	 */
	public int toScreenX(int x)
	{
		int width = this.getWidth();		
		float xecran = (float)(x) * (width-0) / (maxX + 30 - 0);
		return (int) xecran; 
	}
	/**
	 * Converti un point Y à des coordonnes ecran Yecran
	 * @param y
	 * @return Yecran 
	 */
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

	public Vector<VueNoeudLivraison> getListeVueNoeudLivraisons() {
		return listeVueNoeudLivraisons;
	}

	
}
