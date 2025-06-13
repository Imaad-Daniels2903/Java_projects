import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GetPI {


    public static String requestPI(int startingPos, int nDigits) {
        try {
            URL api_url = new URL("https://api.pi.delivery/v1/pi?start=" + startingPos + "&numberOfDigits=" + nDigits + "&radix=10");
            HttpURLConnection conn = (HttpURLConnection) api_url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {

                throw new RuntimeException("Code: " + responseCode);

            } else {

                StringBuilder jasonString = new StringBuilder();
                Scanner scanner = new Scanner(api_url.openStream());

                while (scanner.hasNext()) {
                   jasonString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();
                JSONObject piData = (JSONObject) parser.parse(jasonString.toString());

                return piData.get("content").toString();
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static ArrayList<Integer> divSplit(int nDigits) {
        int splitNum = 1000;
        int listRange = 0;
        ArrayList<Integer> splitList = new ArrayList<Integer>();
        if (nDigits < 1000) {
            splitList.add(nDigits);
        } else {
            if (nDigits % splitNum != 0) {
                listRange = Math.toIntExact(Math.round(Math.floor((double) nDigits / splitNum))) + 1;
                for(int x = 0; x < listRange; x++) {
                    if (x == listRange - 1) {
                        splitList.add(nDigits % splitNum);
                    } else {
                        splitList.add(splitNum);
                    }
                }
                return splitList;

            } else {
                listRange = nDigits / splitNum;
                for (int i = 0; i < listRange; i++) {
                    splitList.add(1000);
                }
                return splitList;
            }
        }
        return splitList;
    }


    public static ArrayList<String> getPiDigits(int nDigits) {
        ArrayList<Integer> splitList = divSplit(nDigits);
        String piSlice = "";
        int startingPos = 0;
        for (int i = 0; i < splitList.toArray().length; i++) {
            piSlice += requestPI(startingPos, splitList.get(i));
            startingPos += 1000;
        }
        ArrayList<String> pieList = new ArrayList<>(Arrays.asList(piSlice));
//        System.out.println(pieList.get(0).length());
        ArrayList<String> peiString = new ArrayList<>(Arrays.asList((pieList.get(0)).split("")));
        return peiString;
    }


}
