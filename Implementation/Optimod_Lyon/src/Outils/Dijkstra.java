package Outils;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import Modele.*;

/**
 * 
 * @author Karim Benhmida
 *
 */
public class Dijkstra {
	private Noeud origine;
	private Noeud destination;
	private Plan plan;
	public static double MatAdjacence[][];
	
	

	/**
	 * calcul de la matrice d'adjacence
	 * @param plan de l'application
	 * @return matrice d'adjacence
	 */
	public static double [][] calculeMatriceAdjacence(Plan plan)
	{ 
    	int Size_Matrice = plan.getListeNoeuds().size();
	    double [][] MatAdjacence = new double [Size_Matrice][Size_Matrice] ; 
		  
		  for (int i = 0; i <Size_Matrice; i++) 
	        {
			for (int j = 0; j <Size_Matrice; j++) 
				MatAdjacence[i][j]=0;
	        }

		 int Nb_Troncon = plan.getListeTroncons().size();
		 Vector<Troncon> liste_Troncons = plan.getListeTroncons();

		 for( int k =0; k<liste_Troncons.size();k++)
			{
		         MatAdjacence[liste_Troncons.get(k).getDepart().getIdNoeud()][liste_Troncons.get(k).getArrivee().getIdNoeud()]=liste_Troncons.get(k).getTemps();
			
			}
	
	return MatAdjacence;
	}
	
	/**
	 * extraire le min 
	 * @param distancecc
	 * @param marque
	 * @return
	 */
	public static int ExtraireMin(double [] distancecc, boolean [] marque){
        double x =Integer.MAX_VALUE; 
        int y=0;
 
        for (int i = 0; i < distancecc.length; i++) {
                                                        if (!marque[i] && distancecc[i]< x) {
 
                                                       y=i;
                                                       x=distancecc[i];
 
                                                                                             }
  
                                                     }      
 
        return y;
    }
	/**
	 * retroune un tableau int des sommets adjacents d'un sommet 
	 * @param idnoeud
	 * @param plan
	 * @return
	 */
	  public static int[] ChercheVoisin(int idnoeud, Plan plan){				
		//construit un tableau des sommets adjacents d'un sommet 
          int count=0;
         
          double [][] MatAdjacence = calculeMatriceAdjacence(plan);
          
          
          for (int i = 0; i < MatAdjacence[idnoeud].length; i++) {
              if (MatAdjacence[idnoeud][i]>0 ) {count ++; }
          }
              final int[]rep = new int [count];      
         
          count=0;    
          for (int i = 0; i < MatAdjacence[idnoeud].length; i++){
               if(MatAdjacence[idnoeud][i]>0){rep[count++]=i;}        
          }     
          return rep;
        }
         
	  
	  
	  public static int[] calculer_cours_chemins(Plan plan, int Source){
		  
          final double [] distancecc = new double [plan.getListeNoeuds().size()];
          final int [] pred = new int [plan.getListeNoeuds().size()];
          final boolean [] marque = new boolean [plan.getListeNoeuds().size()];
   
          for (int i = 0; i < distancecc.length; i++) {
              distancecc[i]=Integer.MAX_VALUE;
              }
          distancecc[Source]=0;
   
          for (int i = 0; i < distancecc.length; i++) {
   
              final int U= ExtraireMin (distancecc, marque);
              marque[U]=true;
   
              final int [] V= ChercheVoisin(U,plan);
              for (int j = 0; j < V.length ; j++) {
                  final int NV = V [j];
                  
                  // attention 
                  double [][] Mat = calculeMatriceAdjacence(plan);
                  final double d = distancecc[U] + Mat[U][NV];   //IG.GetPoids(U, NV);
                  if (d < distancecc[NV]) {
                      distancecc[NV]=d;
                      pred[NV]=U;
                     }
              }
   
           }
          
          
     
          
         return pred;
   
      }
	
	
	  
	  public static ArrayList getShortPath(Plan plan,int idsource , int iddestination )
	  { 
		 
            int [] Pred = calculer_cours_chemins(plan , idsource);
            ArrayList al = new ArrayList();
	  
	        for (int n = 0; n < plan.getListeNoeuds().size(); n++) 
	  
	         {                  
	        	int x=n;
                
                 if(x==iddestination)
                                     {
                	
                                         while (x!=idsource){
                                                              al.add(0,x);
                                                              x=Pred[x];    
                   
                                                             }
                                         al.add(0,idsource);
                
                                      
                
                                      }
             
            }
			return al;
	 
	        	
	  }
	   
	public Dijkstra(Plan plan, Noeud origine, Noeud destination)
	{
		this.plan = plan;
		this.origine = origine;
		this.destination = destination;
	}
	
	
}