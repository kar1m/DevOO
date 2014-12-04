package Modele;

import java.sql.Time;
import java.util.Scanner;
import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Plage horaire de livraison
 * @author Yassine Moreno
 */
public class PlageHoraire {

    public PlageHoraire(int ID) {
    	this.livraisons = new Vector<Livraison>();
    	this.idPlageHoraire = ID;
    	this.heureDebut_H = new Time(0);
    	this.heureFin_H = new Time(0);
    }

    private int idPlageHoraire;
    private Time heureDebut_H;
    private Time heureFin_H;
    private String heureDebut;
    private String heureFin;
    private Vector<Livraison> livraisons;


    /**
     * methode d'initialisation des plages horaires
	 * @param Element XMLnode Element XML contenant les informations pour l'initialisation
	 * @throws Exception liée aux malformations sémantiques et syntaxiques des fichiers
     */
    public void initPlage(Element XMLnode, Vector<Noeud> l ) throws Exception {
        this.heureDebut = XMLnode.getAttribute("heureDebut");
        checkerHeure(this.heureDebut,"Debut");
        this.heureFin = XMLnode.getAttribute("heureFin");
        checkerHeure(this.heureFin,"Fin");
        
        	//Initialisation du vecteur de livraisons
		   Vector<Livraison> livraisonPH = new Vector<Livraison>();
		   NodeList livraisonXML = XMLnode.getElementsByTagName("Livraison");
		   for (int j = 0 ; j<livraisonXML.getLength();j++)
		   {
			   Element livraisonXMLinstance = (Element)livraisonXML.item(j);
			   Livraison nouvelleLivraison = new Livraison();
			   nouvelleLivraison.initLivraison(livraisonXMLinstance,l);
			   livraisonPH.add(nouvelleLivraison);
		   }
		   this.livraisons =  livraisonPH;
    }
    /**
     * methode de parsing/vérification des heures fournies dans le fichier XML
     * @param target string contenant l'heure à verifier
     * @param type heure début ou heure fin
     * @throws Exception liée aux erreurs de parse
     */
    private void checkerHeure(String target,String type) throws Exception
    {
        String pattern = "[\\s]*:[\\s]*";
        
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(target).useDelimiter(pattern);
        int cpt = 0;
        int heure = -1,min = -1,sec = -1;
        try {
			while (sc.hasNext())
			    {
			    	if	(cpt == 0)
			    	{
			    		heure = Integer.parseInt(sc.next());
			    	}
			    	if	(cpt == 1)
			    	{
			    		min = Integer.parseInt(sc.next());
			    	}
			    	if	(cpt == 2)
			    	{
			    		sec = Integer.parseInt(sc.next());
			    	}
			    	cpt++;
			    }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new Exception("Format Heure invalide");
		}
        if (heure<0 || heure>23 || min<0 || min>60 || sec<0 || sec>60)
        {
        	throw new Exception("Format Heure non respectï¿½");
        }
        switch (type)
        {
        case "Debut" :
        	long format = ((((heure-1)*60 + min)*60) + sec)*1000;
            this.heureDebut_H = new Time(format);
            break;
        case "Fin" :
        	long formatFin = ((((heure-1)*60 + min)*60) + sec)*1000;
            this.heureFin_H = new Time(formatFin);
            break;
        }
        
    }

	/**
	 * @return the heureDebut
	 */
	public String getHeureDebut() {
		return heureDebut;
	}
	/**
	 * @return the heureFin
	 */
	public String getHeureFin() {
		return heureFin;
	}
	public Vector<Livraison> getLivraisons() {
		return livraisons;
	}
	
	public int getId()
	{
		return idPlageHoraire;
	}

	public Time getHeureDebut_H() {
		return heureDebut_H;
	}

	public Time getHeureFin_H() {
		return heureFin_H;
	}

}