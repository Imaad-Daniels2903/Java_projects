import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CatApi {
    public static JSONObject getImageData() {
        try {
            URL api_url = new URL(" https://api.thecatapi.com/v1/images/search");
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
                JSONArray piData = (JSONArray) parser.parse(jasonString.toString());
                JSONObject catImgData = (JSONObject) piData.get(0);

                return catImgData;
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
