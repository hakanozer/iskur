package useXml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class MainXml {
    public static void main(String[] args) {

        try {

            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String data = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(data, Parser.xmlParser());
            // xml içindeki elemanların dizi şeklinde gelmesi
            Elements elements = doc.getElementsByTag("Currency");
            for (Element item : elements) {
                String Isim = item.getElementsByTag("Isim").text();
                System.out.println( Isim );
            }

        }catch (Exception ex) {
            System.err.println("Xml Error : " + ex);
        }

    }
}
