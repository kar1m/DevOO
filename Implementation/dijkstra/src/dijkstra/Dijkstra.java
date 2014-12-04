package dijkstra;
import java.util.Vector;



public class Dijkstra {
	private Noeud origine;
	private Noeud destination;
	private Plan plan;
	public static double MatAdjacence[][];
	
	

	// calcul de la matrice d'adjacence 
	
	public static double [][] calculeMatriceAdjacence(Plan plan)
	{ 
		 int Size_Matrice = plan.getListeNoeuds().size();
		  MatAdjacence = new double [Size_Matrice][Size_Matrice] ; 
		 for(int i=0; i<Size_Matrice; i++)
		 {
			 MatAdjacence[i][i] = 0;  
		 }
		
		 int Nb_Troncon = plan.getListeTroncons().size();
		 Vector<Troncon> liste_Troncons = plan.getListeTroncons();
		 
		 // remplissage de la matrice par la valeur des durées des troncons ;
		 
		 for(int i =0; i<liste_Troncons.size();i++)
		 {
			 MatAdjacence[liste_Troncons.get(i).getDepart().getIdNoeud()][liste_Troncons.get(i).getDepart().getIdNoeud()]=liste_Troncons.get(i).getTemps(); ;
			 
		 }
		 return MatAdjacence;
	}
	
	
	// extraire le min 
	
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
	
	
	// retroune un tableau int des sommets adjacents d'un sommet 
	
	  public static int[] ChercheVoisin(int idnoeud, Plan plan){				//construit un tableau des sommets adjacents d'un sommet 
	         
          int count=0;
         
         // double [][] MatAdjacence = calculeMatriceAdjacence(plan);
          
          
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
         

	  
	  // avec destination 
	  
	  
	  public static int [] djikstra (Plan plan, int Source, int destination){
	         
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
                  
                  final double d = distancecc[U] + MatAdjacence[U][NV];   //IG.GetPoids(U, NV);
                  if (d < distancecc[NV]) {
                      distancecc[NV]=d;
                      pred[NV]=U;
                     }
              }
   
           }
   
          return pred;
   
      }
	
	
	public Dijkstra(Plan plan, Noeud origine, Noeud destination)
	{
		this.plan = plan;
		this.origine = origine;
		this.destination = destination;
	}
	
	
}
	
	
	
	
	