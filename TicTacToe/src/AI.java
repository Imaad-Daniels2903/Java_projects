import java.util.*;

public class AI {
    static ArrayList<ArrayList<String>> board; 
    static String turn;
    static String opponentTurn;
    static Boolean defend = false;
    static Boolean attack = false;
    
    public AI(ArrayList<ArrayList<String>> board, String turn) {
        this.board = board;
        this.turn = turn;
        if (turn.equals("X")) {
            this.opponentTurn = "O";
        } else {
            this.opponentTurn = "X";
        }
        
    }

    private static final Map<String, String> moves = Map.of(

            "0,0", "TR",
            "0,1", "TM",
            "0,2", "TL",

            "1,0", "MR",
            "1,1", "MM",
            "1,2", "ML",

            "2,0", "BR",
            "2,1", "BM",
            "2,2", "BL"
    );
    
    public static String aiPlay() {
        int emptyCnt = 0;
        for(ArrayList<String> tempBoard : board) {
            for (String tempSqr : tempBoard) {
                if (tempSqr.equals(" ")) {
                    emptyCnt++;
                }
            }
        }
        
        if (emptyCnt == 9 || emptyCnt == 8 && board.get(1).get(1).equals(" ")) {
            return "MM" + "-" + turn;
        } else if (emptyCnt == 8 && !board.get(1).get(1).equals(" ")){
            ArrayList<String> keys = new ArrayList<>(moves.keySet());
            Random rand = new Random();
            String ranKey = keys.get((rand.nextInt(keys.size())));
            while (moves.get(ranKey).equals(" ")) {
                ranKey = keys.get((rand.nextInt(keys.size())));
            }
            return moves.get(ranKey);
        } else {
            ArrayList<ArrayList<String>> tempBoard = board;
            ArrayList<ArrayList<Integer>> oppWin = new ArrayList<>();
            ArrayList<ArrayList<Integer>> aiWin = new ArrayList<>();
            for (int x = 0; x < tempBoard.size(); x++) {
                for (int y = 0; y < tempBoard.get(x).size(); y++) {
                    if (tempBoard.get(x).get(y).equals(" ")) {
                        tempBoard.get(x).set(y, opponentTurn);
                        if (checkWin(tempBoard)) {
                            oppWin.add((ArrayList<Integer>) Arrays.asList(x, y));
                            defend = true;
                        } else {
                            tempBoard.get(x).set(y, turn);
                        }
                        if (checkWin(tempBoard)) {
                            aiWin.add((ArrayList<Integer>) Arrays.asList(x, y));
                            attack = true;
                        } else {
                            tempBoard.get(x).set(y, " ");
                        }
                    }
                }
            }
            String x;
            String y;
            String outMove;
            if (attack) {
                x = String.valueOf(aiWin.get(0).get(0));
                y = String.valueOf(aiWin.get(0).get(1));
                outMove = moves.get(x + "," + y);
                return outMove;
            } else if (defend && !attack) {
                x = String.valueOf(oppWin.get(0).get(0));
                y = String.valueOf(oppWin.get(0).get(1));
                outMove = moves.get(x + "," + y);
            } else {

            }
        }

        return null;
    }

    public static Boolean checkWin(ArrayList<ArrayList<String>> testBoard) {
        for (int y = 0; y < testBoard.size(); y++) {
            HashSet<String> tempSet = new HashSet<>();

            if (!testBoard.get(0).get(y).equals(" ") &&
                testBoard.get(0).get(y).equals(testBoard.get(1).get(y)) &&
                testBoard.get(1).get(y).equals(testBoard.get(2).get(y))) {
                return true;
            }

            for (int x = 0; x < testBoard.get(y).size(); x++) {
                if (!testBoard.get(y).get(0).equals(" ")) {
                    tempSet.add(testBoard.get(y).get(x));
                }
            }

            if (tempSet.size() == 1) {
                return true;
            }

        }
        if (!testBoard.get(1).get(1).equals(" ") &&
            ((testBoard.get(0).get(0).equals(testBoard.get(1).get(1)) && testBoard.get(1).get(1).equals(testBoard.get(2).get(2))) ||
             (testBoard.get(0).get(2).equals(testBoard.get(1).get(1)) && testBoard.get(1).get(1).equals(testBoard.get(2).get(0))))) {
            return true;
        }

        return false;
    }

    public static void offensivePlay() {
        
    }

    public static void main(String[] args) {

    }
}

