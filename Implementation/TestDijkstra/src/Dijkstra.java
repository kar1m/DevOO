import java.util.LinkedList;

  
        public class Dijkstra {
         
         
            public static int [] djikstra (InputGraph IG, int Source){
         
                final int [] distancecc = new int [IG.GetSize()];
                final int [] pred = new int [IG.GetSize()];
                final boolean [] marque = new boolean [IG.GetSize()];
         
                for (int i = 0; i < distancecc.length; i++) {
                    distancecc[i]=Integer.MAX_VALUE;
                    }
                distancecc[Source]=0;
         
                for (int i = 0; i < distancecc.length; i++) {
         
                    final int U= ExtraireMin.ExtraireMin (distancecc, marque);
                    marque[U]=true;
         
                    final int [] V= IG.ChercheVoisin(U);
                    for (int j = 0; j < V.length ; j++) {
                        final int NV = V [j];
                        final int d = distancecc[U] + IG.GetPoids(U, NV);
                        if (d < distancecc[NV]) {
                            distancecc[NV]=d;
                            pred[NV]=U;
                           }
                    }
         
                 }
         
                
                
                
                
                
                
                
                return pred;
         
            }
         
            public static void AfficheChemin (InputGraph IG, int [] pred, int source, int n , int dest ){
         
         
               
         
                int x=n;
                if(x==dest)
                {
                 LinkedList cheminpc =new LinkedList();
                while (x!=source){
                    cheminpc.add(0,x);
                    x=pred[x];    
                   //System.out.println("hahowa chemin" +cheminpc +" hahiya valeur de " + x);
                }
                cheminpc.add(0,source);
                System.out.println(cheminpc);
                }
                
                //for (int i = 0; i < pred.length ; i++)
                	// System.out.println("la valeur de pred " + pred[i]);
            }
        }