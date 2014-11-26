package Modele;

/**
 * 
 */
public class Client {

    /**
     * 
     */
    public Client() {
    }

    /**
     * 
     */
    private int idClient;

    /**
     * 
     */
    private Noeud noeudAdresse;

    /**
     * @param Noeud adresse 
     * @param int IdClient
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