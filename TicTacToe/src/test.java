public class test {

    public static void main(String[] args) {
        for (int x = 0; x < 5; x++) {
            System.out.println(x);
            for (int y = 0; y < 5; y++) {
                if (String.valueOf(x + y).equals("5")) {
                    break;
                }
            }
        }
    }
}
