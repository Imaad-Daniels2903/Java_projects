import java.util.HashMap;

public class DiceSimulator {

    public class Dice{
        private int dice;
        private int faces;
        private int rolls;
        private int[] weights;
        private int bins;

        public Dice(int dice, int faces, int rolls, int[] weights, int bins){
            this.dice = dice;
            this.faces = faces;
            this.rolls = rolls;
            this.weights = weights;
            this.bins = bins;
        }

        public static int sum(int[] list){
            int sum = 0;
            for(int num : list){
                sum += num;
            }

            return sum;
        }

        public static int[] choices(int[] list, int[] weights, int k){
            int min = 0;
            int max = 99;
            int sum = sum(weights);
            HashMap<Integer, Integer> distro = new HashMap<>();

            

            int randomNum = (int) (Math.random() * (max - min + 1)) + min;
        }

        public void roll(int dice, int faces, int[] weights){
            
        }
    }
    public static void main(String[] args) {
        
    }

}
