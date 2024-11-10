import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Car {
    private int id;
    private String name;
    private String state;
    private String number;
    private transient State carState;

    public Car(int id, String name, String state, String number) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.number = number;
    }

    public State getCarState() {
        return carState;
    }

    public void setCarState(State carState) {
        this.carState = carState;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void changeState(String state) {
        try {
            this.state = state;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCarState(){
        switch (this.state){
            case "onRoute" -> this.carState = new onParking();
            case "onParking" -> this.carState = new onRoute();
        }
    }

    public static List<Car> createCars(int numberOfCars) {
        List<Car> allCars = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 1; i <= numberOfCars; i++) {
            String name = String.valueOf(Logo.random());
            String personalNumber = String.format("%03d", rnd.nextInt(1500));
            Car car = new Car(i, name, "onRoute", personalNumber);
            allCars.add(car);
        }
        return allCars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return " Car " +
                "id=" + id +
                " name='" + name + '\'' +
                " state='" + state + '\'' +
                " number='" + number + '\'';
    }


}
