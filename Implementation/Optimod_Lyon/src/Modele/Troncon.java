package Modele;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import org.w3c.dom.*;

/**
 * Arc oriente reliant exactement deux noeuds, avec un nom, une vitesse et une longueur, un noeud d'origine et un noeud de destination
 */
public class Troncon {

    /**
     * Arc orientereliant exactement deux noeuds, avec un nom, une vitesse et une longueur, un noeud d'origine et un noeud de destination
     */
    public Troncon() {
    }

    /**
     * methode d'initialisation du troncon
	 * @param xmlChunck Element XML contenant les informations pour l'initialisation
	 * @param entree noeud de depart du troncon
	 * @param sortie noeud d'arrivee du troncon
	 * @throws Exception liee aux malformations semantiques et syntaxiques des fichiers
     */
    public void initTrancon (Element xmlChunk,Noeud entree, Noeud sortie) throws Exception
    {
    	NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

    	try {
			this.vitesse = format.parse(xmlChunk.getAttribute("vitesse")).doubleValue();
			positifChecker(this.vitesse);
			this.longueur = format.parse(xmlChunk.getAttribute("longueur")).doubleValue();
			positifChecker(this.longueur);
			this.nomRue = xmlChunk.getAttribute("nomRue");
	    	this.depart = entree;
	    	this.arrivee = sortie;
	    	this.temps = longueur/vitesse;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Erreur dans les Troncons declares");
		}
    }
    
    private void positifChecker(double target) throws Exception
    {
    	if (target < 0)
    	{
    		throw new Exception();
    	}
    }
    
    /**
     * Vitesse de circulation moyenne du trançon
     */
    private double vitesse = 0;

    /**
     * Longueur du trançon (mètres)
     */
    private double longueur = 0;

    /**
     * Nom du trançon
     */
    private String nomRue;

    /**
     * Noeud Arrivee
     */
    private Noeud arrivee;

    /**
     * Noeud Depart
     */
    private Noeud depart;
    
    private double temps = 0;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Troncon [vitesse=" + vitesse + ", longueur=" + longueur
				+ ", nomRue=" + nomRue + ", arrivee=" + arrivee.getIdNoeud() + ", depart="
				+ depart.getIdNoeud() + "]";
	}


	public Noeud getArrivee() {
		return arrivee;
	}


	public Noeud getDepart() {
		return depart;
	}
	
	public double getTemps() {
		return temps;
	}


	public double getVitesse() {
		return vitesse;
	}


	public double getLongueur() {
		return longueur;
	}


	public String getNomRue() {
		return nomRue;
	}
	
	

}