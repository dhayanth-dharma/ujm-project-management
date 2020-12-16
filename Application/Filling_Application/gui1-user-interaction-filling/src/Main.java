public class Main
{
    public static  void powermangement(int voltage, float frequency ,float intensity) {
        if (voltage >=220 & voltage<=240 ){
            System.out.println("its work sucssufly"); }
            else{
                System.out.println(" there are problem with voltage");
            }
        
   
        
        if (frequency >=220 & frequency <=240 ){
            System.out.println("its work sucssufly"); }
          else {
               System.out.println(" there are problem with frecuency");
           }
           if (intensity >=25 & intensity <=30 ){
            System.out.println("its work sucssufly"); }
          else {
               System.out.println(" there are problem with intensity");
           }
        
   
    } 
	public static void main(String[] args) {
        
        powermangement(220,120,30);
        
	}
}