package Modele;

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
    public void initLivraison(Element XMLnode) {
        this.idLivraison = Integer.parseInt(XMLnode.getAttribute("id"));
        this.destinataire = new Client();
        destinataire.initClient(Integer.parseInt(XMLnode.getAttribute("adresse")), Integer.parseInt(XMLnode.getAttribute("client")));
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