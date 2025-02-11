import java.util.*;

public class SimpleGraph {

    ArrayList<ArrayList<Integer>> midwestMap;

    public SimpleGraph(int numberOfCities) {
        midwestMap = new ArrayList<>(numberOfCities);
    }

   public static void main(String[] args) {
        SimpleGraph demo = new SimpleGraph(5);
        ArrayList city0 = new ArrayList<>();
        city0.add(1);
        city0.add(4);
        demo.midwestMap.add(city0);
   }
}
