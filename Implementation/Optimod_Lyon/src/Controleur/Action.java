package Controleur;

/**
 * @author Yassine Moreno
 */
public abstract class Action {

    public Action() {
    }
    
    /**
	 * methode d'execution d'une action
	 * @return booléen de confirmation de l'execution
	 * @exception exception relevée lors du chargement d'éléments (plan/livraisons) liés aux cas d'erreurs spécifiés
	 */
    public abstract boolean Executer();
    /**
	 * methode d'annulation d'une action
	 * @return booléen de confirmation de l'annulation
	 */
    public abstract boolean Annuler();

}