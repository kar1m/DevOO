package Modele;

import java.util.Vector;

import org.w3c.dom.Element;

/**
 * 
 */
public class Livraison {

    /**
     * 
     */
    public Livraison() {
    }

    /**
     * 
     */
    private int idLivraison;

    /**
     * 
     */
    private Client destinataire;

    /**
     * @param Element XMLnode
     */
    public void initLivraison(Element XMLnode, Vector<Noeud> l) {
        this.idLivraison = Integer.parseInt(XMLnode.getAttribute("id"));
        Noeud noeudClient = l.get(Integer.parseInt(XMLnode.getAttribute("adresse")));
        this.destinataire = new Client();
        destinataire.initClient( noeudClient , Integer.parseInt(XMLnode.getAttribute("client")));
    }

	/**
	 * @return the idLivraison
	 */
	public int getIdLivraison() {
		return idLivraison;
	}


	/**
	 * @return the destinataire
	 */
	public Client getDestinataire() {
		return destinataire;
	}

}