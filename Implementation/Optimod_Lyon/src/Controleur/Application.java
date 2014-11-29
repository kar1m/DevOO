package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.JTable;

import Modele.DataWareHouse;
import Modele.Livraison;
import Modele.PlageHoraire;
import Outils.*;
import Vue.AjoutLivraison;
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
						this.listeAnnulation.clear();	
						vue.updateUndoRedo(listeExecution.size()>0, listeAnnulation.size()>0);
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
						this.listeAnnulation.clear();
						
						vue.chargerLivraison(modele.getLivraisonData());
						vue.getPlan().repaint();
						vue.updateUndoRedo(listeExecution.size()>0, listeAnnulation.size()>0);
					}
					break;
				case Proprietes.CHARGER_PLAN :
					XMLhandler outilXML = (XMLhandler) args.get(0);
					String path = (String) args.get(1);
					ActionChargerPlan action2 = new ActionChargerPlan(modele, outilXML, path);
					action2.Executer();
					vue.chargerPlan(modele.getPlanApp());
					vue.repaint();
					vue.logText("Plan chargé");
					vue.getBtnChargerDemandeLivraison().setEnabled(true);
					break;
				case Proprietes.CHARGER_LIVRAISON : 
					ActionChargerLivraison action3 = new ActionChargerLivraison(modele);
					action3.Executer();
					vue.chargerLivraison(modele.getLivraisonData());
					vue.repaint();
					vue.logText("Demande de livraison chargée");
					break;
				case Proprietes.UNDO :
					if(listeExecution.size() > 0)
					{
						Action actionAnnulable = this.listeExecution.lastElement();
						actionAnnulable.Annuler();
						this.listeExecution.removeElementAt(listeExecution.size()-1);
						listeAnnulation.addElement(actionAnnulable);
						
						vue.chargerLivraison(modele.getLivraisonData());
						vue.getPlan().repaint();
						vue.updateUndoRedo(listeExecution.size()>0, listeAnnulation.size()>0);
					}
					break;
				case Proprietes.REDO : 
					if(listeAnnulation.size() > 0 )
					{
						Action actionAnnulee = listeAnnulation.lastElement();
						actionAnnulee.Executer();
						listeAnnulation.removeElementAt(listeAnnulation.size()-1);
						listeExecution.addElement(actionAnnulee);	
						
						vue.chargerLivraison(modele.getLivraisonData());
						vue.getPlan().repaint();
						vue.updateUndoRedo(listeExecution.size()>0, listeAnnulation.size()>0);
					}
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
	
	public void gererClickDroit(MouseEvent e, boolean livraison, VueNoeud noeud)
	{
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) 
		{
			VuePopup pop = new VuePopup(livraison); 
			pop.show(e.getComponent(), e.getX(), e.getY());
			
			if(livraison)
			{
				VueNoeudLivraison a = (VueNoeudLivraison) noeud; 
				
				pop.getB().addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						ArrayList<Object> args = new ArrayList<Object>();
						args.add(a.getLivraison());
						gererCommande(Proprietes.SUPP_LIVRAISON, args);		
					}					
				});
			}else{
				pop.getA().addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						AjoutLivraison md1 = new AjoutLivraison(vue, "exemple avec JDialog modal", true);
					
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
				gererClickDroit(e,true, a);		
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
				gererClickDroit(e,false, a);		
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
		}
		if(e.getSource() == vue.getBtnChargerDemandeLivraison())
		{
			gererCommande(Proprietes.CHARGER_LIVRAISON,null);
		}
		if(e.getSource() == vue.getBtnUndo())
		{
			gererCommande(Proprietes.UNDO,null);
		}
		if(e.getSource() == vue.getBtnRedo())
		{
			gererCommande(Proprietes.REDO,null);
		}
	}
}