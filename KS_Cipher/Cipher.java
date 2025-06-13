import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Cipher {
    public static ArrayList<Integer> getASCII(String userInput) {
      ArrayList<Integer> asciiList = new ArrayList<>();
      for (int x = 0; x < userInput.length(); x++) {
          char ch = userInput.charAt(x);
          int asciiVal = (int) ch;
          asciiList.add(asciiVal);
      }
      return asciiList;
    }


    public static ArrayList<ArrayList<Integer>> getBinary(ArrayList<Integer> asciiList) {
        ArrayList<ArrayList<Integer>> bigBinList = new ArrayList<>();
        for (int asciival : asciiList){
            int tempval = asciival;
            int[] byteList = {128, 64, 32, 16, 8, 4, 2, 1};
            ArrayList<Integer> binList = new ArrayList<>();
            for (int i : byteList) {
                if (i > tempval) {
                    binList.add(0);
                    continue;
                } else {
                    tempval -= i;
                    binList.add(1);
                }
            }
            bigBinList.add(binList);
        }
        return bigBinList;
    }


    public static ArrayList<ArrayList<Integer>> addMagic(ArrayList<ArrayList<Integer>> binList) {
    ArrayList<String> piDigits = GetPI.getPiDigits((binList.size()) * 8);
    ArrayList<ArrayList<Integer>> bigBinList = new ArrayList<>();
    int[] magicKey = {4, 9, 7, 3, 3, 9, 2, 1};
    int piCnt = 0;
        for (ArrayList<Integer> sBin : binList) {
            ArrayList<Integer> sTemp = new ArrayList<>();
            for (int x = 0; x < sBin.size(); x++) {
                sTemp.add(x, sBin.get(x) + magicKey[x] + Integer.parseInt(piDigits.get(piCnt)) + 64);
            }
            bigBinList.add(sTemp);
        }
        return bigBinList;
    }


    public static ArrayList<ArrayList<Character>> getChar(ArrayList<ArrayList<Integer>> asciiList) {
        ArrayList<ArrayList<Character>> conChars = new ArrayList<>();
        for (ArrayList<Integer> asciiSet : asciiList) {
            ArrayList<Character> cTempList = new ArrayList<>();
            for (int x = 0; x < asciiSet.size(); x++) {
                int numTemp = asciiSet.get(x);
                cTempList.add(((char) numTemp));
            }
            conChars.add(cTempList);
        }
        return conChars;
    }


    public static ArrayList<String> getString(ArrayList<ArrayList<Character>> charList) {
       ArrayList<String> stringList = new ArrayList<>();
       for (ArrayList<Character> charSet : charList) {
           StringBuilder sbTemp = new StringBuilder();
           for (char ch : charSet) {
               sbTemp.append(ch);
           }
           stringList.add(sbTemp.toString());
       }
       return stringList;
    }


    public static ArrayList<ArrayList<Character>> caesarCipher(ArrayList<ArrayList<Character>> charList) {
        ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        ArrayList<Character> cipherKey = new ArrayList<>(Arrays.asList('*', '$', '[', 'I', 'e', '#', ':', '"', 'f', '.', '6', '0', '>', 'n', 'j', '|', '(', 'Y', '7', '-', ']', 'P', 'U', '^', 'F', 'q'));
        ArrayList<ArrayList<Character>> outList = new ArrayList<>();

        for (ArrayList<Character> chrSet : charList) {
            ArrayList<Character> cTemp = new ArrayList<>();
            for (Character sChar : chrSet) {
                cTemp.add(cipherKey.get(alphabet.indexOf(sChar)));
            }
            outList.add(cTemp);
        }
        return outList;
    }


    public static void getOutput(String userInput) {
        ArrayList<Integer> asciiList = Cipher.getASCII(userInput);
        ArrayList<ArrayList<Integer>> binaryList = Cipher.getBinary(asciiList);
        ArrayList<ArrayList<Integer>>  reAsciiList = Cipher.addMagic(binaryList);
        ArrayList<ArrayList<Character>> conCharacters = Cipher.getChar(reAsciiList);
        ArrayList<ArrayList<Character>> caesarList = Cipher.caesarCipher(conCharacters);
        ArrayList<String> stringList = Cipher.getString(caesarList);
        String outputString = String.join("", stringList);
        System.out.println(outputString);
    }


}
