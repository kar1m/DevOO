package Modele;

import java.util.Vector;
/**
 * Itinéraire entre 2 noeuds
 */
public class Chemin {
	/**
     * Troncons composant le chemin
     */
    private Vector<Troncon> listeTroncons;
    
    public Chemin() {
    	this.listeTroncons = new Vector<Troncon>();
    }
    
	public Vector<Troncon> getListeTroncons() {
		return listeTroncons;
	}
	/**
	 * Ajout de troncon au chemin actuel
	 * @param troncon à ajouter
	 */
	public void addTroncon(Troncon troncon) {
		listeTroncons.add(troncon);
	}
	
	public int getTemps() {
		int sommeTemps = 0;
		
		for (int i = 0; i < listeTroncons.size(); i++)
		{
			sommeTemps += listeTroncons.get(i).getTemps();
		}
		
		return sommeTemps;
	}


}