package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Modele.DataWareHouse;
import Modele.Livraison;
import Modele.PlageHoraire;
import Modele.Noeud;
import Outils.*;
import Vue.Fenetre;
import Vue.VueNoeud;
import Vue.VueNoeudLivraison;
import Vue.VuePopup;

/**
 * 
 */
public class Application implements MouseListener, ActionListener{

	private DataWareHouse modele;
	private Fenetre vue;
	private Vector<Action> listeAnnulation;
	private Vector<Action> listeExecution;
	
    public Application(Fenetre vue, DataWareHouse modele) {
    	this.listeAnnulation = new Vector<Action>();
    	this.listeExecution = new Vector<Action>();
    	this.modele = modele;
    	this.vue = vue; 
    }

    public Application()
    {
    	
    }
    	

    /**
     * 
     */
    public void gererCommande(String commande, ArrayList<Object> args) {
        try {
				switch (commande)
				{
				case Proprietes.AJOUTER_LIVRAISON :
					if(args.size() == 2)
					{
						PlageHoraire a = (PlageHoraire) args.get(0);
						Livraison l = (Livraison) args.get(1);
						ActionAjouterLivraison action = new ActionAjouterLivraison(modele, a, l);
						action.Executer();
						this.listeExecution.addElement(action);
					}
					break;
				case Proprietes.CALC_TOURNEE :
					break;
				case Proprietes.SUPP_LIVRAISON :
					if(args.size() == 1)
					{
						Livraison l = (Livraison) args.get(0);
						ActionSupprimerLivraison action1 = new ActionSupprimerLivraison(modele,l);
						action1.Executer();
						this.listeExecution.addElement(action1);
					}
					break;
				case Proprietes.CHARGER_PLAN :
					XMLhandler outilXML = (XMLhandler) args.get(0);
					String path = (String) args.get(1);
					ActionChargerPlan action2 = new ActionChargerPlan(modele, outilXML, path);
					action2.Executer();
					break;
				case Proprietes.CHARGER_LIVRAISON : 
					ActionChargerLivraison action3 = new ActionChargerLivraison(modele);
					action3.Executer();
					break;
				case Proprietes.UNDO :
					Action actionAnnulable = this.listeExecution.lastElement();
					actionAnnulable.Annuler();
					this.listeExecution.removeElementAt(listeExecution.size());
					listeAnnulation.addElement(actionAnnulable);
					break;
				case Proprietes.REDO : 
					Action actionAnnulee = listeAnnulation.lastElement();
					actionAnnulee.Executer();
					listeAnnulation.removeElementAt(listeAnnulation.size());
					listeExecution.addElement(actionAnnulee);
					break;
				case Proprietes.SAVE:
					break;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 
     */
    public void initApplication() {
        // TODO implement here
    }
    
	/**
	 * @return the listeAnnulation
	 */
	public Vector<Action> getListeAnnulation() {
		return listeAnnulation;
	}

	/**
	 * @return the listeExecution
	 */
	public Vector<Action> getListeExecution() {
		return listeExecution;
	}

	/**
	 * @return the modele
	 */
	public DataWareHouse getModele() {
		return modele;
	}
	

	
	//-------------------------------------Mouse Listener--------------------------------------------//
	public void clickTable(MouseEvent e)
	{
		JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow();
        
		for(int i=0; i<vue.getPlan().getListeVueNoeudLivraisons().size(); i++)
			vue.getPlan().getListeVueNoeudLivraisons().get(i).selected= (i==row);
			
		for(VueNoeud a : vue.getPlan().getListeVueNoeuds())
			a.selected=false;
		
		vue.getPlan().repaint();
	}
	
	public void gererClickDroit(MouseEvent e, boolean livraison, Noeud noeudConcerne)
	{
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) 
		{
			VuePopup pop = new VuePopup(livraison); 
			pop.show(e.getComponent(), e.getX(), e.getY());
			
			if(livraison)
			{
				pop.getB().addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("Clique sur Supprimer");
					}					
				});
			}else{
				pop.getA().addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						System.out.println("Clique sur Ajouter");
					}					
				});
			}
		}
	}
	
	public boolean gererClickLivraison(MouseEvent e)
	{
		boolean livraisonSelected = false; 

		for(int i=0; i < vue.getPlan().getListeVueNoeudLivraisons().size(); i++)
		{
			VueNoeudLivraison a = vue.getPlan().getListeVueNoeudLivraisons().get(i);
			if(a.clickDessus(e.getX(), e.getY()))
			{
				a.selected = true;	
				livraisonSelected = true;
				gererClickDroit(e,true, a.getNoeudAssocie());		
				vue.getTable().getT().setRowSelectionInterval(i, i);
				vue.logText("Clique sur une livraison");
			}else{
				a.selected = false;
			}
		}
				
		//Si livraison selectionnee, on delesectionne tous les noeuds du plan 
		//Sinon, on deselectionne toutes les row du JTable
		if(livraisonSelected)
		{
			for(VueNoeud a : vue.getPlan().getListeVueNoeuds())
				a.selected = false;
		}else
		{
			vue.getTable().getT().removeRowSelectionInterval(0, vue.getPlan().getListeVueNoeudLivraisons().size()-1);
		}
		
		return livraisonSelected; 
	}
	
	public boolean gererClickPlan(MouseEvent e)
	{
		boolean selected = false; 
		
		for(VueNoeud a : vue.getPlan().getListeVueNoeuds())
		{
			if(a.clickDessus(e.getX(), e.getY()))
			{
				a.selected = true;
				selected = true; 
				gererClickDroit(e,false, a.getNoeudAssocie());		
				vue.logText("Clique sur X : " + a.getNoeudAssocie().getX() + " Y : " + a.getNoeudAssocie().getY());
			}else{
				a.selected = false;
			}
		}	

		return selected;
	}
	
	public void clickPlan(MouseEvent e)
	{
		// TODO Auto-generated method stub

		if(vue.getPlan().getListeVueNoeudLivraisons() != null)
		{
			//Si on a bien cliqué sur une livraison, on peut ne pas faire la suite
			if(gererClickLivraison(e))
			{
				vue.getPlan().repaint();
				return;	
			}
		}
				
		if(vue.getPlan().getListeVueNoeuds() != null)
		{
			if(!gererClickPlan(e))
				vue.logText("Clique sur autre chose qu'un noeud");
		}
				
		vue.getPlan().repaint();
	}

	
	public void mouseClicked(MouseEvent e) {	
		if(e.getSource() == vue.getTable().getT())
		{
			clickTable(e);
		}else if(e.getSource() == vue.getPlan())
		{
			clickPlan(e);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == vue.getBtnChargerPlan()){
			
			XMLhandler outilXML = new XMLhandler();
			String path = outilXML.selectXML();
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(outilXML);
			args.add(path);
			gererCommande(Proprietes.CHARGER_PLAN, args);
			vue.chargerPlan(modele.getPlanApp());
			vue.repaint();
			vue.logText("Plan chargé");
			vue.getBtnChargerDemandeLivraison().setEnabled(true);
		}
		if(e.getSource() == vue.getBtnChargerDemandeLivraison())
		{
			gererCommande(Proprietes.CHARGER_LIVRAISON,null);
			vue.chargerLivraison(modele.getLivraisonData());
			vue.repaint();
			vue.logText("Demande de livraison chargée");
		}
	}
}