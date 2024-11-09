import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Action {
    List<Car> allCars = new ArrayList<>();
    Map<Car, List<String>> journal = new HashMap<>();
    Map<Car, LocalDateTime> parkingTimes = new HashMap<>();
    static List<Car> parkedCars = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    long bankParking = 0;
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime end = now.plusDays(7);
    LocalDateTime current = now;


    void createCars(int numberOfCars) {
        for (int i = 1; i <= numberOfCars; i++) {
            String logo = Logo.random().toString();
            Car car = new Car(i, logo, "onRoute", "124");
            allCars.add(car);
        }
    }

    public void simulateParking() {
        Random rnd = new Random();

        while (current.isBefore(end) || current.isEqual(end)) {
            current = current.plusMinutes(5);

            int probabilityIn = rnd.nextInt(34);
            int probabilityOut = rnd.nextInt(34);

            attemptToParkCar(probabilityIn);
            attemptToExitCar(probabilityOut);
        }
    }

    public void attemptToParkCar(int probability) {
        if (probability < 3 && parkedCars.size() < 20) {
            Random rnd = new Random();
            Car car = allCars.get(rnd.nextInt(allCars.size()));
            parkedCars.add(car);
            parkingTimes.put(car, current);
            car.changeState("onParking");
            allCars.remove(car);

            journal.putIfAbsent(car, new ArrayList<>());
            journal.get(car).add("Въезд: " + current.format(formatter));

            System.out.println("Парковка: " + car + " время: " + current.format(formatter));
        } else if (parkedCars.size() >= 20) {
            System.out.println("В парковке нет свободных мест. Приходите позже через 5 минут.");
        }
    }

    public void attemptToExitCar(int probability) {
        if (probability < 3 && !parkedCars.isEmpty()) {
            Random rnd = new Random();
            Car car = parkedCars.get(rnd.nextInt(parkedCars.size()));
            car.changeState("onRoute");
            allCars.add(car);
            parkedCars.remove(car);

            long fee = calculateParkingFee(car);
            bankParking += fee;

            journal.get(car).add("Выезд: " + current.format(formatter));

            System.out.println("Возвращается на маршрут: " + car + " время: " + current.format(formatter));
        }
    }

    public long calculateParkingFee(Car car) {
        LocalDateTime entryTime = parkingTimes.get(car);
        long minutesParked = java.time.Duration.between(entryTime, current).toMinutes();
        return (minutesParked / 5) * 10;
    }

    public void printJournal() {
        System.out.println("\nЖурнал въездов и выездов:");
        for (Map.Entry<Car, List<String>> entry : journal.entrySet()) {
            Car car = entry.getKey();
            List<String> events = entry.getValue();
            System.out.println(car + " - События: " + events);
        }
    }
}
