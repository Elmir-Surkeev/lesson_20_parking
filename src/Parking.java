public class Parking extends Car{
    private int date;
    private Car car;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Parking(int date, Car car) {
        super(car.getId(), car.getName(), car.getState(), car.getNumber());
        this.date = date;
        this.car = car;
    }
}
