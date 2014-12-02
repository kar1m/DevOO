import java.util.LinkedList;

        public class InputGraph {
         
        	 public int [][] arc;       // Matrice d'adjacense
             public Object [] sommet;   //objet sommet    
         
             public static int count;
         
           public InputGraph (int n){
               arc = new int[n][n];
               sommet = new Object[n];
         
           }
        public int GetSize (){			// récupère la taille du sommet
           return sommet.length;
         
        }
         
        public void SetSommet(int node, Object label){		//permet d'ajouter un sommet   
          sommet[node]=label;
         
        }
         
        public Object GetSommet(int node){		// récupère la valeur d'un sommet
          return sommet[node];
         
        }
         
         
        public void AddArc(int SommetDep, int SommetArr, int dist){		// ajoute un arc
          arc [SommetDep][SommetArr]=dist;
         
        }
         
        public int GetPoids(int SommetDep, int SommetArr){			//récupère la distance
          return arc [SommetDep][SommetArr];
         
        }
         
         
         
         
        public void AfficheMatrice(){						//affiche la matrice du graphe
         
          for (int j = 0; j < arc.length; j++) {
        	  System.out.println("");
              System.out.println( " DE " + sommet[j] +" jusqu'à ");
              System.out.println("");
          for (int i = 0; i < arc[j].length; i++) {
              if (arc[j][i]>0) 
              System.out.println(sommet[i] + " la distance est de " +arc[j][i] );
              }
          }
         
        }
        public  int [] ChercheVoisin(int node){				//construit un tableau des sommets adjacents d'un sommet 
         
          count=0;
         
          for (int i = 0; i < arc[node].length; i++) {
              if (arc[node][i]>0 ) {count ++; }
          }
              final int[]rep = new int [count];      
         
          count=0;    
          for (int i = 0; i < arc[node].length; i++){
               if(arc[node][i]>0){rep[count++]=i;}        
          }     
          return rep;
        }
         
        public static void main(String[] args) {
         
          InputGraph ex = new InputGraph (4);
          ex.SetSommet(0, "New york");
          ex.SetSommet(1, "Paris");
          ex.SetSommet(2, "Londres");
          ex.SetSommet(3, "New delhi");
          ex.AddArc(0, 1, 400);
          ex.AddArc(0, 2, 200);
          ex.AddArc(1, 2, 300);
          ex.AddArc(2, 1, 100);
          ex.AddArc(1, 3, 100);
          ex.AddArc(2, 3, 500);
          ex.AddArc(2, 0, 200);
         
          ex.AfficheMatrice();
          System.out.println("");
          System.out.println("Les plus court chemin à partir du sommet source sont :");
         
          final int [] pred = Dijkstra.djikstra(ex,0);
          for (int n = 0; n < 4; n++) {
         
          Dijkstra.AfficheChemin(ex,pred, 0, n , 3);
          } 
        }
       
     
         

            public static void AfficheChemin (InputGraph IG, int [] pred, int source, int n){
         
         
                LinkedList cheminpc =new LinkedList();
         
                int x=n;
                while (x!=source){
                    cheminpc.add(source, IG.GetSommet(x)+"=====>");
                    x=pred[x];    
                }
                cheminpc.add(source, IG.GetSommet(source)+"=====>");
         
                System.out.println("--------------------------------------------");
                System.out.println(cheminpc);
         
            }
        }
       
        
        	


