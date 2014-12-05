package Controleur;

/**
 * @author Yassine Moreno
 */
public abstract class Action {

    public Action() {
    }
    
    /**
	 * methode d'execution d'une action
	 * @return booleen de confirmation de l'execution
	 * @exception exception relevee lors du chargement d'elements (plan/livraisons) lies aux cas d'erreurs specifies
	 */
    public abstract boolean Executer();
    /**
	 * methode d'annulation d'une action
	 * @return booleen de confirmation de l'annulation
	 */
    public abstract boolean Annuler();

}