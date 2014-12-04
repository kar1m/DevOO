package Modele;

import java.sql.Time;
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

    public Time tempsPassage;
    public Time getTempsPassage() {
		return tempsPassage;
	}

	public void setTempsPassage(Time tempsPassage) {
		this.tempsPassage = tempsPassage;
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
     * @throws Exception 
     */
    public void initLivraison(Element XMLnode, Vector<Noeud> l) throws Exception {
        this.idLivraison = Integer.parseInt(XMLnode.getAttribute("id"));
        try {
			Noeud noeudClient = l.get(Integer.parseInt(XMLnode.getAttribute("adresse")));
			this.destinataire = new Client();
			destinataire.initClient( noeudClient , Integer.parseInt(XMLnode.getAttribute("client")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Client Inconnu");
		}
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

	public void setDestinataire(Client destinataire) {
		this.destinataire = destinataire;
	}
	
	public void generateIdLivraison()
	{
		this.idLivraison = (int)(Math.random()*50000);
	}

}