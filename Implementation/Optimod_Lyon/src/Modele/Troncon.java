package Modele;

import java.util.*;

/**
 * Arc orienté reliant exactement deux noeuds, avec un nom, une vitesse et une longueur, un noeud d'origine et un noeud de destination
 */
public class Troncon {

    /**
     * Arc orienté reliant exactement deux noeuds, avec un nom, une vitesse et une longueur, un noeud d'origine et un noeud de destination
     */
    public Troncon() {
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