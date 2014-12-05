package Modele;

/**
 * Demandeur de livraison
 */
public class Client {

    /**
     * 
     */
    public Client() {
    }

    /**
     * Identifiant du client
     */
    private int idClient;

    /**
     * Noeud de l'adresse du client
     */
    private Noeud noeudAdresse;

    /**
     * methode d'initialisation du client
     * @param Noeud adresse du client
     * @param int IdClient identifiant affecte au client
     */
    public void initClient(Noeud adresse, int id_Client) {
        this.noeudAdresse = adresse;
        this.idClient = id_Client;
    }

	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * @return the idNoeudAdresse
	 */
	public Noeud getNoeudAdresse() {
		return noeudAdresse;
	}

}