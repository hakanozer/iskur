package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cars {

    private List<Car> cars = new ArrayList<>();
    String[] arrColor = {"Black", "Red", "Green", "Gray", "Blue", "Purple", "Yellow"};

    public List<Car> carsResult() {
        Random rd = new Random();
        for (int i = 0; i < 10; i++) {
            int random = rd.nextInt(arrColor.length - 1);
            Car c = new Car("Title-" + i, i * i, arrColor[random], i * 10 );
            cars.add(c);
        }

        return cars;
    }

}
