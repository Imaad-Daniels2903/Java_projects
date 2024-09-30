import java.util.*;

class Main{

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to clear console.");
        }
    }

    public static void main(String[] args){

        //Initializing Arrays and ArrayLists
        ArrayList<String[]> playerHand = new ArrayList<>();
        ArrayList<String[]> computerHand = new ArrayList<>();
        ArrayList<String[]> unoDeck = new ArrayList<>();
        ArrayList<String[]> playPile = new ArrayList<>();
        boolean gameState =false;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        //Constructing class
        Game_function gameFunction = new Game_function(unoDeck, playerHand, computerHand, playPile);

        //Generating Deck
        gameFunction.generateDeck();

        //Dealing cards using method in Game_function class and displaying them
        gameFunction.deal();

        System.out.println("Would you like to play: [Y/N]");
        String playerInput = scanner.nextLine();
        if (Objects.equals(playerInput.toUpperCase(Locale.ROOT), "Y")){
        gameState = true;
        }else{
            System.exit(0);
        }

        while (gameState){

            gameFunction.displayHands();
            gameFunction.displayPlayCard();
            System.out.println("Play a card using index 0 - " + (playerHand.size() - 1));
            playerInput = scanner.nextLine();
            gameFunction.playCard(Integer.parseInt(playerInput));
            gameFunction.AIplay();
            //clearConsole();
            if (playerHand.isEmpty() || computerHand.isEmpty()){
                System.out.println("GAME OVER!!");
                gameState = false;
            }
        }
    }
}