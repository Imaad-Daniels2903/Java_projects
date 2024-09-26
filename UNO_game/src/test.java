import java.util.*;

public class test {
    public static void main(String[] args){
        Random random = new Random();
        for (int ranLoop = 0; ranLoop <= 100; ranLoop++){
            int ranNum = random.nextInt((8 - 1) + 1);
            System.out.println(ranNum);

        }
    }
}
