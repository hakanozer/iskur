package models;

import java.util.ArrayList;
import java.util.List;

public class Result {
    public List<String> data() {
        List<String> ls = new ArrayList<>();
        ls.add("İstanbul");
        ls.add("İzmir");
        ls.add("Ankara");
        ls.add("Bursa");
        ls.add("Samsun");
        ls.add("Edirne");
        return ls;
    }
}
