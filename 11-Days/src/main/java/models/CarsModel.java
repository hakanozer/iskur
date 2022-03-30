package models;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CarsModel {

    Cars listCars = new Cars();
    List<Car> ls = new ArrayList<>();
    public CarsModel() {
        this.ls = listCars.carsResult();
    }


    public DefaultTableModel model() {
        DefaultTableModel md = new DefaultTableModel();
        // add column
        md.addColumn("Title");
        md.addColumn("Hp");
        md.addColumn("Color");
        md.addColumn("Km");

        for ( Car item : ls ) {
            Object[] row = { item.getTitle(), item.getHp(), item.getColor(), item.getKm() };
            md.addRow(row);
        }

        return md;
    }

    public void add( Car car ) {
        listCars.cars.add(car);
    }

    public void remove(int row) {
        listCars.cars.remove(row);
    }

}
