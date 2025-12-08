import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DiceSimulator {
    private int dice;
    private int faces;
    private int rolls;
    private int[] weights;
    private int bins;

    public DiceSimulator(int dice, int faces, int rolls, int[] weights, int bins){
        this.dice = dice;
        this.faces = faces;
        this.rolls = rolls;
        this.weights = weights;
        this.bins = bins;
    }

    private int sum(int[] list){
        int sum = 0;
        for(int num : list){
            sum += num;
        }

        return sum;
    }

    private int[] choices(int[] list, int[] weights, int k){
        int sum = sum(weights);
        ArrayList<Integer> faces = new ArrayList<>();
        HashMap<Integer, Integer> distro = new HashMap<>();
        
        for(int i = 0; 1 < weights.length; i++){
            distro.put(i, (weights[i] / sum * 100));
            for(int j = 0; j < distro.get(i); j++){
                faces.add(list[i]);
            }
        }
        int min = 0;
        int max = faces.size() - 1;
        ArrayList<Integer> outList = new ArrayList<>();
        
        for(int i = 0; i < k; i++){
             int randomNum = (int)(Math.random() * (max - min + 1)) + min;

        }

        return  faces.get(randomNum);
    }

    public void roll(int dice, int faces, int[] weights){
        
    }
}
public static void main(String[] args) {
    

}
