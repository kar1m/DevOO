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
		 
		// System.out.println(" le nombre de troncons "+liste_Troncons.size());
		 
		 for( int k =0; k<liste_Troncons.size();k++)
			{//System.out.println("depart "+liste_Troncons.get(k).getDepart().getIdNoeud());
		 //System.out.println("Arrivé "+liste_Troncons.get(k).getArrivee().getIdNoeud());
		 //System.out.println(" valeur  duration "+ liste_Troncons.get(k).getTemps());
		 MatAdjacence[liste_Troncons.get(k).getDepart().getIdNoeud()][liste_Troncons.get(k).getArrivee().getIdNoeud()]=liste_Troncons.get(k).getTemps();
			
			}
		 // remplissage de la matrice par la valeur des durées des troncons ;
		// int f= 0;
		 
		 //for( f =0; f<liste_Troncons.size();f++)
		 //{
			// MatAdjacence[liste_Troncons.get(f).getDepart().getIdNoeud()][liste_Troncons.get(f).getArrivee().getIdNoeud()]=liste_Troncons.get(f).getTemps(); 
			/* for(  j=0; i<liste_Troncons.size();i++)
			 { System.out.println(" id noeud de départ"+liste_Troncons.get(i).getDepart().getIdNoeud());
			    System.out.println(" id noeud de arrivé"+liste_Troncons.get(i).getArrivee().getIdNoeud());}
			 
				 if(liste_Troncons.get(i).getDepart().getIdNoeud()== i && liste_Troncons.get(j).getDepart().getIdNoeud() == j)
				 { MatAdjacence[i][j]=liste_Troncons.get(i).getTemps(); }
					 */
			 //MatAdjacence[liste_Troncons.get(i).getDepart().getIdNoeud()][liste_Troncons.get(i).getArrivee().getIdNoeud()]=liste_Troncons.get(i).getTemps(); ;
			
			
		// System.out.println("Benhmida"+MatAdjacence[43][44]);
		 
		/*for(int i=0; i<100 ; i++){
			for(int j=0; j<100 ; j++){
				System.out.print("hadi i : "+ i +"w hadi j :"+ j  + "w hadi sa valeur"+  MatAdjacence[i][j] );}
			System.out.println(" YOO");
		}*/
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
          
          
          //return Cour_chemin;
          
         return pred;
   
      }
	
	
	  
	  public static ArrayList Get_Short_Path(Plan plan,int idsource , int iddestination )
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
                
                                       //  System.out.println(al);
                
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