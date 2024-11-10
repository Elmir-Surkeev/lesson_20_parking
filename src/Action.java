import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Action {
    private List<Car> allCars = new ArrayList<>(Car.createCars(200));
    private List<Car> parkedCars = new ArrayList<>();
    private Map<String, List<String>> journal = new HashMap<>();
    private static final int MAX_PARKING_SPACE = 20;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int MAX_DAY_FOR_TEST = 30;
    private static final LocalTime PAID_START_TIME = LocalTime.of(9, 0);
    private static final LocalTime PAID_END_TIME = LocalTime.of(21, 0);
    long bankParking = 0;
    private LocalDateTime currentTime = LocalDateTime.now();
    private Random rnd = new Random();


    public void simulateParking() {
        LocalDateTime endTime = currentTime.plusDays(MAX_DAY_FOR_TEST);

        while (!currentTime.isAfter(endTime)) {
            currentTime = currentTime.plusMinutes(5);

            for (Car car : allCars) {
                if (car.getState().equals("onRoute") && rnd.nextInt(100) < 3) {
                    attemptToParkCar(car);
                } else if (car.getState().equals("onParking") && rnd.nextInt(100) < 3) {
                    attemptToExitCar(car);
                }
            }
        }
    }

    private void attemptToParkCar(Car car) {
        if (parkedCars.size() < MAX_PARKING_SPACE) {
            car.changeState("onParking");
            parkedCars.add(car);
            journal.putIfAbsent(car.getRegistrationNumber(), new ArrayList<>());
            journal.get(car.getRegistrationNumber()).add("Въезд: " + currentTime.format(FORMATTER));
        }
    }

    private void attemptToExitCar(Car car) {
        car.changeState("onRoute");
        parkedCars.remove(car);

        LocalDateTime entryTime = LocalDateTime.parse(journal.get(car.getRegistrationNumber()).get(journal.get(car.getRegistrationNumber()).size() - 1).split(": ")[1], FORMATTER);
        long parkedMinutes = java.time.Duration.between(entryTime, currentTime).toMinutes();

        if (parkedMinutes > 30 && isWithinPaidHours(entryTime, currentTime)) {
            long fee = (parkedMinutes / 5) * 10;
            bankParking += fee;
        }
        journal.get(car.getRegistrationNumber()).add("Выезд: " + currentTime.format(FORMATTER));
    }

    private boolean isWithinPaidHours(LocalDateTime entryTime, LocalDateTime exitTime) {
        LocalTime startTime = entryTime.toLocalTime().isBefore(PAID_START_TIME) ? PAID_START_TIME : entryTime.toLocalTime();
        LocalTime endTime = exitTime.toLocalTime().isAfter(PAID_END_TIME) ? PAID_END_TIME : exitTime.toLocalTime();
        return !startTime.isAfter(endTime);
    }

    public long calculateMoneyForOneDay(LocalDate date) {
        long dayMoney = 0;
        for (Map.Entry<String, List<String>> entry : journal.entrySet()) {
            List<String> events = entry.getValue();
            for (int i = 0; i < events.size() - 1; i += 2) {
                String entryTimeStr = events.get(i).split(": ")[1];
                String exitTimeStr = events.get(i + 1).split(": ")[1];
                LocalDateTime entryTime = LocalDateTime.parse(entryTimeStr, FORMATTER);
                LocalDateTime exitTime = LocalDateTime.parse(exitTimeStr, FORMATTER);

                if (entryTime.toLocalDate().equals(date) && isWithinPaidHours(entryTime, exitTime)) {
                    long minutesParked = java.time.Duration.between(entryTime, exitTime).toMinutes();
                    if (minutesParked > 30) {
                        dayMoney += (minutesParked / 5) * 10;
                    }
                }
            }
        }
        return dayMoney;
    }



    public void printJournal() {
        for (Map.Entry<String, List<String>> entry : journal.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
