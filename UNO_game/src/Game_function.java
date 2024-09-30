import java.util.*;


public class Game_function {

    private final ArrayList<String[]> deck;
    private final ArrayList<String[]> playersHand;
    private final ArrayList<String[]> computerHand;
    private final ArrayList<String[]> playPile;
    public static boolean turn = true;


    public Game_function(ArrayList<String[]> deck, ArrayList<String[]> playersHand, ArrayList<String[]> computerHand, ArrayList<String[]> playPile){
        this.computerHand = computerHand;
        this.deck = deck;
        this.playersHand = playersHand;
        this.playPile = playPile;

    }



    public void generateDeck(){
        String[] colours = new String[] {"R", "B", "G", "Y"};
        String[] powerCards = new String[] {"Reverse", "Block", "+2"};
        String[] wildCards = new String[] {"anyColour", "+4"};
        //Generating Numbered Cards
        for (int numberLoop = 0; numberLoop <= 9; numberLoop++){
            if (numberLoop == 0){
                for (int colourLoop = 0; colourLoop < colours.length; colourLoop++){
                    deck.add(new String[] {colours[colourLoop], String.valueOf(numberLoop)});
                }
            } else {
                for (int colourLoop = 0; colourLoop < colours.length; colourLoop++){
                    deck.add(new String[] {colours[colourLoop], String.valueOf(numberLoop)});
                    deck.add(new String[] {colours[colourLoop], String.valueOf(numberLoop)});
                }
            }
        }
        //Generating Power Cards
        for (int powerCardsLoop = 0; powerCardsLoop <= 2; powerCardsLoop++){
            for (int colourLoop = 0; colourLoop < colours.length; colourLoop++){
                deck.add(new String[] {colours[colourLoop], powerCards[powerCardsLoop]});
                deck.add(new String[] {colours[colourLoop], powerCards[powerCardsLoop]});
            }
        }
        //Generating Wild Cards
        for (int wildCardLoop = 0; wildCardLoop <= 1; wildCardLoop++){
            for (int cardLoop = 0; cardLoop <= 3; cardLoop++){
                deck.add(new String[] {"Wild", wildCards[wildCardLoop]});
            }
        }
    }



    public void deal(){
        Collections.shuffle(deck);
        for (int deckLoop = 0; playersHand.size() <= 6; deckLoop++) {
            playersHand.add(deck.get(0));
            deck.remove(0);
            computerHand.add(deck.get(0));
            deck.remove(0);
        }
        playPile.add(deck.get(0));
        deck.remove(0);
    }



    public void displayHands(){
        String[][] playerHand = new String[playersHand.size()][];
        String[][] compHand = new String[computerHand.size()][];

        for (int displayArray = 0; displayArray < playersHand.size(); displayArray++) {
            playerHand[displayArray] =playersHand.get(displayArray);
        }

        for (int displayArray = 0; displayArray < computerHand.size(); displayArray++) {
            compHand[displayArray] =computerHand.get(displayArray);
        }

        System.out.println("Players hand: " + Arrays.deepToString(playerHand));
        System.out.println(playersHand.size());
        System.out.println("Computers hand: " + Arrays.deepToString(compHand));
        System.out.println(computerHand.size());
    }



    public void displayDeck(){
        String[][] deckArray = new String[deck.size()][];
        for (int displayArray = 0; displayArray < deck.size(); displayArray++) {
            deckArray[displayArray] = deck.get(displayArray);
        }
        System.out.println("Deck: " + Arrays.deepToString(deckArray));
    }

    

    public void displayPlayCard(){
        System.out.println("Play pile card: " + Arrays.toString(playPile.get(playPile.size() - 1)));
    }



    public void playCard(int cardIndex){
        if (turn){
            String[] pileCard = playPile.get(playPile.size() -1);
            if (Game_function.playLogic((playersHand.get(cardIndex)), pileCard)){
                playPile.add(playersHand.get(cardIndex));
                addCards((playersHand.get(cardIndex)[1]), computerHand);
                System.out.println("Player played: " + Arrays.toString(playersHand.get(cardIndex)));
                playersHand.remove(cardIndex);
                //System.out.println(playersHand.size());
            }else{
                System.out.println("Can't play that card");
                turn = true;
            }
        }
    }



    public void AIplay(){
        if (!turn){
            for (int AiLoop = 0; AiLoop < computerHand.size(); AiLoop++){
                if (Game_function.playLogic(computerHand.get(AiLoop) ,playPile.get(playPile.size() - 1))){
                    playPile.add(computerHand.get(AiLoop));
                    addCards((computerHand.get(AiLoop)[1]),playersHand);
                    System.out.println("Computer played: " + Arrays.toString(computerHand.get(AiLoop)));
                    computerHand.remove(AiLoop);
                    break;
                }
            }
            turn = false;
        }


        //System.out.println(computerHand.size());
    }



    public static boolean playLogic(String[] playedCard, String[] pileCard){
        String[] colours = new String[]{"R", "B", "G", "Y"};
        Random random = new Random();
        if (Objects.equals(playedCard[0], "Wild")){
            if (turn){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose Colour [R, G, B, Y]:");
            String colour = scanner.nextLine().toUpperCase();
            playedCard[0] = colour;
            turn = true;
            return true;
            }else{
                int ranColour = random.nextInt(((3) + 1));
                playedCard[0] = colours[ranColour];
                turn = false;
                return true;
            }
        }

        if ((Objects.equals(playedCard[0], pileCard[0])) || (Objects.equals(playedCard[1], pileCard[1]))){
            if (turn){
                turn = false;
            }else{
                turn = true;
            }
            return true;
        }

        return false;

    }

    public void addCards(String cardAmount, ArrayList<String[]> target){
        switch (cardAmount){
            case "+4":
                for (int addCards = 0; addCards <= 3; addCards++){
                    target.add(deck.get(0));
                    deck.remove(0);
                }
                break;
            case "+2":
                for (int addCards = 0; addCards <= 1; addCards++){
                    target.add(deck.get(0));
                    deck.remove(0);
                }
                break;
        }
    }

}
