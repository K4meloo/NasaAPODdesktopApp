import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDataDAO {
    private static final String APIkey = "A7I1ELL57Qg666Uneh4BwPEpKGrAaA1E43mkIRcI";
    private static final String url = "https://api.nasa.gov/planetary/apod?api_key=" + APIkey + "&date=";

    public static ImageData getImageData(String date) throws IOException {
        URL url = new URL(ImageDataDAO.url + date);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept", "application/json");
        int status = con.getResponseCode();
        System.out.println(status);
        if (status > 299) {
            System.out.println("Error");
        } else {
            System.out.println("Success");

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                String stringJson = response.toString();

                JSONObject json = new JSONObject(stringJson);

                return new ImageData(json.getString("title"), json.getString("explanation"), json.getString("date"), json.getString("url"));
            }
        }
        return null;
    }
}
