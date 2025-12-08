import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DiceSimulator {
    private int dice;
    private int faces;
    private int rolls;
    private List<Integer> weights;
    private int bins;

    public DiceSimulator(int dice, int faces, int rolls, List<Integer> weights, int bins){
        this.dice = dice;
        this.faces = faces;
        this.rolls = rolls;
        this.weights = weights;
        this.bins = bins;
    }

    private int sum(List<Integer> list){
        int sum = 0;
        for(int num : list){
            sum += num;
        }

        return sum;
    }

    private ArrayList<Integer> choices(ArrayList<Integer> list, List<Integer> weights, int k){
        int sum = sum(weights);
        ArrayList<Integer> items = new ArrayList<>();
        HashMap<Integer, Integer> distro = new HashMap<>();
        
        for(int i = 0; i < weights.size(); i++){
            distro.put(i, (weights.get(i) / sum * 100));
            for(int j = 0; j < distro.get(i); j++){
                items.add(list.get(i));
            }
        }
        int min = 0;
        int max = items.size() - 1;
        ArrayList<Integer> outList = new ArrayList<>();
        
        for(int i = 0; i < k; i++){
             int randomNum = (int)(Math.random() * (max - min + 1)) + min;
            if(outList.contains(randomNum)){
                 randomNum = (int)(Math.random() * (max - min + 1)) + min;
        
            }
            else{
                outList.add(randomNum);
            }
        }

        return  outList;
    }

    public int roll(int dice, int faces, List<Integer> weights){
        ArrayList<Integer> facesList = new ArrayList<>();
        ArrayList<Integer> outcomes = new ArrayList<>();
        for(int i = 1; i < faces + 1; i++){
            facesList.add(i);
        }
        for(int i = 0; i < dice; i++){
            outcomes.add(choices(facesList, weights, 1).get(0));
        }
        return outcomes.stream().mapToInt(Integer::intValue).sum(); 
    }
    
    public List<Integer> getOutcomes(){
        List<Integer> outcomes = new ArrayList<>();
        for(int i = 0; i < this.rolls; i++){
            outcomes.add(roll(this.dice, this.faces, this.weights));
        }
        return outcomes;
    }

//    public HashMap<Integer, Integer> getDistrubution(){
//
//    }
    public void getStats(){
        List<Integer> outcomes = getOutcomes();
        Collections.sort(outcomes);
        HashMap<Integer, Integer> frequencyDstribution = new HashMap<>();

        int N = this.rolls;
        double mean = sum(outcomes) / N;
        
        if(outcomes.size() % 2 == 0){
            double median = (outcomes.get(outcomes.size() / 2) + outcomes.get((outcomes.size() / 2) - 1)) / 2;

        }
        else{
            int median = (outcomes.get(Math.round(outcomes.size() / 2)));
        }

        System.out.println(mean);
        System.out.println(outcomes);
//        System.out.println(median);
    }

    public static void main(String[] args) {
        List<Integer> weights = new ArrayList<>(List.of(1, 1, 1, 1, 2, 2));
        DiceSimulator dice = new DiceSimulator(2, 6, 100, weights, 1);
        dice.getStats();
    }
}