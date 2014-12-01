	public class ExtraireMin {
            
        	public static int ExtraireMin(int [] distancecc, boolean [] marque){
                int x =Integer.MAX_VALUE; 
                int y=0;
         
                for (int i = 0; i < distancecc.length; i++) {
                     if (!marque[i] && distancecc[i]< x) {
         
                        y=i;
                        x=distancecc[i];
         
                    }
         
                }      
         
                return y;
            }
         }