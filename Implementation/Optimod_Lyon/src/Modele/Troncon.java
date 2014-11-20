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

    
    public void initTrancon (Element xmlChunk,Noeud entree, Noeud sortie)
    {
    	NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);

    	try {
			this.vitesse = format.parse(xmlChunk.getAttribute("vitesse")).doubleValue();
			this.longueur = format.parse(xmlChunk.getAttribute("longueur")).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	this.nomRue = xmlChunk.getAttribute("nomRue");
    	this.depart = entree;
    	this.arrivee = sortie;
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

}