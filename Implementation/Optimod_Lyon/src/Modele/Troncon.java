package Modele;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import org.w3c.dom.*;

/**
 * Arc orienté reliant exactement deux noeuds, avec un nom, une vitesse et une longueur, un noeud d'origine et un noeud de destination
 */
public class Troncon {

    /**
     * Arc orienté reliant exactement deux noeuds, avec un nom, une vitesse et une longueur, un noeud d'origine et un noeud de destination
     */
    public Troncon() {
    }

    
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
			throw new Exception("Erreur dans les Tron�ons d�clar�s");
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
     * 
     */
    private Noeud arrivee;

    /**
     * 
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
	
	

}