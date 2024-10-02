import java.util.*;


public class Game_function {

    private final ArrayList<String[]> deck;
    private final ArrayList<String[]> playersHand;
    private final ArrayList<String[]> computerHand;
    private final ArrayList<String[]> playPile;
    public static boolean turn = true;
    public static final String[] colours = new String[] {"R", "B", "G", "Y"};
    public static final String[] powerCards = new String[] {"Reverse", "Block", "+2"};
    public static final String[] wildCards = new String[] {"anyColour", "+4"};

    public Game_function(ArrayList<String[]> deck, ArrayList<String[]> playersHand, ArrayList<String[]> computerHand, ArrayList<String[]> playPile){
        this.computerHand = computerHand;
        this.deck = deck;
        this.playersHand = playersHand;
        this.playPile = playPile;

    }



    public void generateDeck(){
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
        int topCard = 0;
        for (int deckLoop = 0; playersHand.size() <= 6; deckLoop++) {
            playersHand.add(deck.get(0));
            deck.remove(0);
            computerHand.add(deck.get(0));
            deck.remove(0);
        }
        while ((Arrays.asList(powerCards).contains(deck.get(topCard)[1])) || (Arrays.asList(wildCards).contains(deck.get(topCard)[1]))){
            topCard++;
        }
        playPile.add(deck.get(topCard));
        deck.remove(topCard);
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



    public void playCard(){
        Scanner scanner = new Scanner(System.in);
        if (turn){
            System.out.println("Play a card using index 0 - " + (playersHand.size() - 1));
            String cardIndex = scanner.nextLine();
            String[] pileCard = playPile.get(playPile.size() -1);
            switch (cardIndex.toUpperCase()){
                case "D":
                    playersHand.add(deck.get(0));
                    deck.remove(0);
                    displayHands();
                    turn = true;
                    break;

                case "C":
                    turn = false;
                    break;
                default:
                    if (Game_function.playLogic((playersHand.get(Integer.parseInt(cardIndex))), pileCard)){
                        playPile.add(playersHand.get(Integer.parseInt(cardIndex)));
                        powerCards(playersHand.get(Integer.parseInt(cardIndex)));
                        addCards((playersHand.get(Integer.parseInt(cardIndex))[1]), computerHand);
                        System.out.println("Player played: " + Arrays.toString(playersHand.get(Integer.parseInt(cardIndex))));
                        playersHand.remove(Integer.parseInt(cardIndex));
                        //System.out.println(playersHand.size());
                    }else{
                        System.out.println("Can't play that card");
                        turn = true;
                    }
            }
        }
    }



    public void AIplay(){
        boolean computerDraw = true;
        if (!turn){
            int playCount = 0;
            for (int AiLoop = 0; AiLoop < computerHand.size(); AiLoop++){
                if (Game_function.playLogic(computerHand.get(AiLoop) ,playPile.get(playPile.size() - 1))){
                    playPile.add(computerHand.get(AiLoop));
                    powerCards(computerHand.get(AiLoop));
                    addCards((computerHand.get(AiLoop)[1]),playersHand);
                    System.out.println("Computer played: " + Arrays.toString(computerHand.get(AiLoop)));
                    computerHand.remove(AiLoop);
                    playCount++;
                    break;
                }
            }
            if (playCount >= computerHand.size() || !computerDraw){
                if (computerDraw){
                    computerHand.add(deck.get(0));
                    computerDraw = false;
                }
                if (!computerDraw){
                    turn = true;
                    computerDraw = true;
                }

            }

        }


        //System.out.println(computerHand.size());
    }



    public static boolean playLogic(String[] playedCard, String[] pileCard){
        Random random = new Random();
        if (Objects.equals(playedCard[0], "Wild")){
            if (turn){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose Colour [R, G, B, Y]:");
            String colour = scanner.nextLine().toUpperCase();
            playedCard[0] = colour;
            if (Objects.equals(playedCard[1], "anyColour")){
                turn = false;
            }
            return true;
            }else{
                int ranColour = random.nextInt(((3) + 1));
                playedCard[0] = colours[ranColour];
                if (Objects.equals(playedCard[1], "anyColour")){
                    turn = true;
                }
                return true;
            }
        }

        if ((Objects.equals(playedCard[0], pileCard[0])) || (Objects.equals(playedCard[1], pileCard[1]))){
            if (turn && !(Arrays.asList(powerCards).contains(playedCard[1]))){
                turn = false;
            }else{
                if (!turn && !(Arrays.asList(powerCards).contains(playedCard[1]))){
                turn = true;
                }else{
                    turn = false;
                }
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
                keepTurn();
                break;
            case "+2":
                for (int addCards = 0; addCards <= 1; addCards++){
                    target.add(deck.get(0));
                    deck.remove(0);
                }
                keepTurn();
                break;
        }
    }



    public void powerCards(String[] playedCard){
        switch (playedCard[1]){
            case "Reverse", "Block", "+2":
                keepTurn();
        }
    }



    public static void keepTurn(){
        if (turn){
            turn = true;
        }else{
            turn = false;
        }
    }

}
