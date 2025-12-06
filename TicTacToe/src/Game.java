import java.util.*;

public class Game {

    private static Boolean endGame =  false;
    private static int playCount = 0;
    private static String userInput;
    private static String winner;
    private static String playerTurn;
    private static final ArrayList<ArrayList<String>> board = new ArrayList<>();
    private static ArrayList<String> moveList = new ArrayList<>();
    private static  ArrayList<String> piece = new ArrayList<>(Arrays.asList("O", "X"));
    private static final Map<String, ArrayList<Integer>> moves = Map.of(

            "TR", new ArrayList<>(Arrays.asList(0, 0)),
            "TM", new ArrayList<>(Arrays.asList(0, 1)),
            "TL", new ArrayList<>(Arrays.asList(0, 2)),

            "MR", new ArrayList<>(Arrays.asList(1, 0)),
            "MM", new ArrayList<>(Arrays.asList(1, 1)),
            "ML", new ArrayList<>(Arrays.asList(1, 2)),

            "BR", new ArrayList<>(Arrays.asList(2, 0)),
            "BM", new ArrayList<>(Arrays.asList(2, 1)),
            "BL", new ArrayList<>(Arrays.asList(2, 2))
    );

    public static void start(int players){

        if (players == 2) {
            Scanner gameScan = new Scanner(System.in);

            while(!endGame) {
                setBoard();
                userInput = gameScan.nextLine();
                if (userInput.equals("close")) {
                    endGame =  true;

                } else {
                    if (userInput.contains("-")) {
                        String[] stringSplit = userInput.toUpperCase().split("-");
                        if (moves.containsKey(stringSplit[0]) && stringSplit.length == 2 && piece.contains(stringSplit[1])) {
                            if (!moveList.contains(userInput.toUpperCase())) {
                                if (playerTurn == null) {
                                    playerTurn = stringSplit[1];
                                    play(stringSplit);
                                    swithTurn();
                                    checkWin();
                                } else {
                                    if (stringSplit[1].equals(playerTurn)) {
                                        moveList.add(userInput.toUpperCase());

                                        play(stringSplit);
                                        swithTurn();
                                        checkWin();

                                    } else {
                                        System.out.println("IT'S NOT UR TURN!!");
                                    }
                                }


                            } else {
                                System.out.println("Invalid move");
                            }
                        } else {
                            System.out.println("Invalid move please use move format (eg.MM-X, BR-O)");
                        }
                    } else {
                        System.out.println("Invalid move please use move format (eg.MM-X, BR-O)");
                    }
                }

            }
        }
    }

    public static void setBoard() {
        if (board.size() == 0) {
            String[] cords = {"T", "M", "B"};
            for (int x = 0 ; x < 3; x++) {
                System.out.println( cords[x] + "    "  +   "|"  +  "     "  +  "|"  +  "    " );
                if (x != 2) {
                    System.out.println(" ——— " + "+"  + " ——— "  + "+" + " ———");
                }

                ArrayList<String> tempList = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    tempList.add(" ");
                }
                board.add(tempList);
            }
            System.out.println("  " + "R" + "  "  +   " "  +  "  " + "M" + "  "  +  " "  +  "  " + "L" + " " );
            System.out.println("To play use co-ordinates as shown above and X or O (eg.TL-O, BM-X)");
        } else {
            for (int y = 0; y < 3; y++) {
                System.out.println(" " + board.get(y).get(0) + "  "  +   "|"  +  "  " + board.get(y).get(1) + "  "  +  "|"  +  "  " + board.get(y).get(2) + " " );
                if (y != 2) {
                    System.out.println("——— " + "+"  + " ——— "  + "+" + " ———");
                }
            }
        }
    }

    public static void play(String[] playerMove) {
        ArrayList<Integer> posList = moves.get(playerMove[0]);
        board.get(posList.get(0)).set(posList.get(1), playerMove[1]);
        playCount++;
    }

    public static void checkWin() {
        for (int y = 0; y < board.size(); y++) {
            HashSet<String> tempSet = new HashSet<>();

            if (board.get(0).get(y).equals(board.get(1).get(y)) && board.get(1).get(y).equals(board.get(2).get(y)) && !board.get(0).get(y).equals(" "))  {
                winner = board.get(0).get(y);
                setBoard();
                System.out.println(winner + " WINS!!");
                endGame = true;
                break;
            }

            for (int x = 0; x < board.get(y).size(); x++) {
                if (board.get(y).get(0).equals(" ")) {
                    continue;
                } else {
                tempSet.add(board.get(y).get(x));
                }
            }

            if (tempSet.size() == 1 ) {
                winner = board.get(y).get(0);
                setBoard();
                System.out.println(winner + " WINS!!");
                endGame = true;
                break;
            }

        }

        if ((board.get(0).get(0).equals(board.get(1).get(1)) && board.get(1).get(1).equals(board.get(2).get(2))) &&  !board.get(1).get(1).equals(" ") || (board.get(0).get(2).equals(board.get(1).get(1)) && board.get(1).get(1).equals(board.get(2).get(0))) &&  !board.get(1).get(1).equals(" ")) {
            winner = board.get(1).get(1);
            setBoard();
            System.out.println(winner + " WINS!!");
            endGame = true;
        }

        if (playCount == 9) {
            setBoard();
            System.out.println("TIE!!");
            endGame = true;
        }
    }
    

    public static void swithTurn() {
        if (playerTurn.equals("X")) {
            playerTurn = "O";
        } else {
            playerTurn = "X";
        }
    }

    public static void main(String[] args) {
        start(1);
    }
}
