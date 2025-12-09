            import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DiceSimulator {
    private final int dice;
    private final int faces;
    private final int rolls;
    private final List<Integer> weights;

    public DiceSimulator(int dice, int faces, int rolls, List<Integer> weights){
        this.dice = dice;
        this.faces = faces;
        this.rolls = rolls;
        this.weights = weights;
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
            int percentage = (int) Math.round((double) (weights.get(1)) / sum * 100);
            distro.put(i, percentage);
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
                outList.add(items.get(randomNum));
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
            outcomes.add(choices(facesList, weights, 1).getFirst());
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

    
    public void getStats(){
        int median;
        List<Integer> outcomes = getOutcomes();
        Collections.sort(outcomes);
        HashMap<Integer, Integer> frequencyDstribution = new HashMap<>();

        double mean = (double) sum(outcomes) / this.rolls;

        if(outcomes.size() % 2 == 0){
            median = (int)Math.round((double) (outcomes.get(outcomes.size() / 2) + outcomes.get((outcomes.size() / 2) - 1)) / 2);

        }
        else{
            median = (outcomes.get(Math.round((float) (outcomes.size()) / 2)));
        }

        for(int outcome: outcomes){
            if(frequencyDstribution.containsKey(outcome)){
                frequencyDstribution.put(outcome, frequencyDstribution.get(outcome) + 1);
            }
            else{
                frequencyDstribution.put(outcome, 1);
            }
        }

        

        System.out.println(mean);
        System.out.println(median);
        System.out.println(outcomes);
        System.out.println(frequencyDstribution);
    }

    public static void main(String[] args) {
        List<Integer> weights = new ArrayList<>(List.of(1, 2, 3, 2, 1, 5));
        DiceSimulator dice = new DiceSimulator(5    , 6, 1000, weights);
        dice.getStats();
    }
}