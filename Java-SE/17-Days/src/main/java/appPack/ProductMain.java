package appPack;

import com.google.gson.Gson;
import models.Bilgiler;
import models.ProductData;
import org.jsoup.Jsoup;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductMain {
    public static void main(String[] args) {

        try {

            String url = "https://www.jsonbulut.com/json/product.php";
            Map<String, String> hm = new LinkedHashMap<>();
            hm.put("ref", "c7c2de28d81d3da4a386fc8444d574f2");
            hm.put("start", "0");
            String data = Jsoup.connect(url).data(hm).timeout(15000).ignoreContentType(true).get().text();
            Gson gson = new Gson();
            ProductData pData = gson.fromJson(data, ProductData.class);
            //boolean status = pData.getProducts().get(0).getDurum();
            List<Bilgiler> bilgilers = pData.getProducts().get(0).getBilgiler();
            for ( Bilgiler item : bilgilers ) {
                System.out.println( item.getProductName() );
            }

        }catch (Exception ex) {
            System.err.println("Service Error : " + ex);
        }

    }
}
