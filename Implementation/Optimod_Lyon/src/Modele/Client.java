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
    private int idNoeudAdresse;

    /**
     * @param Noeud adresse 
     * @param int IdClient
     */
    public void initClient(int id_adresse, int id_Client) {
        this.idNoeudAdresse = id_adresse;
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
	public int getIdNoeudAdresse() {
		return idNoeudAdresse;
	}

}