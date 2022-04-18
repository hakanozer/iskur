package appPack;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;

import java.util.HashMap;

public class MainApp {

    public static void main(String[] args) {

        try {

            String url = "https://newsapi.org/v2/top-headlines";
            HashMap<String, String> hm = new HashMap<>();
            hm.put("country", "tr");
            hm.put("category", "business");
            hm.put("apiKey", "38a9e086f10b445faabb4461c4aa71f8");

            String data = Jsoup.connect(url).data(hm).timeout(15000).ignoreContentType(true).get().text();
            Gson gson = new Gson();
            JsonObject obj = gson.fromJson(data, JsonObject.class);
            String status = obj.get("status").getAsString();
            System.out.println( status );
            // array
            JsonArray arr = obj.getAsJsonArray("articles");
            for( JsonElement item : arr ) {
                JsonObject o = item.getAsJsonObject();
                String title = o.get("title").getAsString();
                System.out.println( title );
            }

        }catch (Exception ex) {
            System.err.println("Service Error : " + ex);
        }

    }

}
