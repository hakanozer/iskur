package models;

import props.City;
import util.Const;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Result {
    public List<City> data() {
        List<City> ls = new ArrayList<>();
        ls.add(new City(0, "İstanbul"));
        ls.add( new City(1, "İzmir" ));
        ls.add( new City(2, "Ankara"));
        ls.add( new City(3, "Bursa") );
        ls.add( new City(4, "Samsun") );
        ls.add( new City(5, "Edirne") );
        return ls;
    }

    public String single(String cid, HttpServletResponse response) {
        String city = "";
        try {
            int cCid = Integer.parseInt( cid );
            city = data().get(cCid).getName();
        }catch (Exception ex) {
            try {
                response.sendRedirect(Const.base);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return city;
    }

}
