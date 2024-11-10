import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Action {
    private List<Car> allCars = new ArrayList<>(Car.createCars(200));
    private Map<Car, List<String>> journal = new HashMap<>();
    private Map<Car, LocalDateTime> parkingTimes = new HashMap<>();
    private static List<Car>  PARKED_CARS = new ArrayList<>();
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static int MAX_PARKING_SPACE = 20;
    long bankParking = 0;
    private LocalDateTime now = LocalDateTime.now();
    private LocalDateTime end = now.plusDays(7);
    private LocalDateTime current = now;
    private Random rnd = new Random();


    public void simulateParking() {
        while (current.isBefore(end) || current.isEqual(end)) {
            current = current.plusMinutes(5);

            int probabilityIn = rnd.nextInt(34);
            int probabilityOut = rnd.nextInt(34);

            attemptToParkCar(probabilityIn);
            attemptToExitCar(probabilityOut);
        }
    }

    public void attemptToParkCar(int probability) {
        if (probability < 3 && PARKED_CARS.size() < MAX_PARKING_SPACE) {
            Car car = allCars.get(rnd.nextInt(allCars.size()));
            PARKED_CARS.add(car);
            parkingTimes.put(car, current);
            car.changeState("onParking");
            allCars.remove(car);

            journal.putIfAbsent(car, new ArrayList<>());
            journal.get(car).add("Въезд: " + current.format(FORMATTER));

            System.out.println("Парковка: " + car + " время: " + current.format(FORMATTER));
        } else if (PARKED_CARS.size() >= MAX_PARKING_SPACE) {
            System.out.println("В парковке нет свободных мест. Приходите позже через 5 минут.");
        }
    }

    public void attemptToExitCar(int probability) {
        if (probability < 3 && !PARKED_CARS.isEmpty()) {
            Car car = PARKED_CARS.get(rnd.nextInt(PARKED_CARS.size()));
            car.changeState("onRoute");
            allCars.add(car);
            PARKED_CARS.remove(car);

            long fee = calculateParkingFee(car);
            bankParking += fee;

            journal.get(car).add("Выезд: " + current.format(FORMATTER));

            System.out.println("Возвращается на маршрут: " + car + " время: " + current.format(FORMATTER));
        }
    }

    public long calculateParkingFee(Car car) {
        LocalDateTime entryTime = parkingTimes.get(car);
        long minutesParked = java.time.Duration.between(entryTime, current).toMinutes();
        return (minutesParked / 5) * 10;
    }

    public int calculateMoneyForOneDay(LocalDate date){
        int day = 0;
        for (Map.Entry<Car, LocalDateTime> entry : parkingTimes.entrySet()) {
            Car car = entry.getKey();
            LocalDateTime entryTime = entry.getValue();

            if (entryTime.toLocalDate().equals(date)) {
                long fee = calculateParkingFee(car);
                day += fee;
            }
        }
        return day;

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
