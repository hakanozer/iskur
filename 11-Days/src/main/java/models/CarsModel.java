package models;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarsModel {

    public DefaultTableModel model() {
        DefaultTableModel md = new DefaultTableModel();
        // add column
        md.addColumn("Title");
        md.addColumn("Hp");
        md.addColumn("Color");
        md.addColumn("Km");

        Cars cars = new Cars();
        List<Car> ls = cars.carsResult();
        for ( Car item : ls ) {
            Object[] row = { item.getTitle(), item.getHp(), item.getColor(), item.getKm() };
            md.addRow(row);
        }

        return md;
    }

}
