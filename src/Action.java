import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Action {
    public static void main() {
        Random rnd = new Random();
        List<Car> allCars = new ArrayList<>();
        Map<Car, List<String>> journal = new HashMap<>();
        List<String> parkedCars = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Капитал парковки
        long bankParking = 0;
        // Создаем автомобили
        for (int i = 1; i <= 200; i++) {
            String logo = Logo.random().toString();
            Car car = new Car(i, logo, "onRoute", "124");
            allCars.add(car);
        }

        // Логика времени
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusDays(30);
        LocalDateTime current = now;

        // Хранение информации о парковке
        //Map<String, Car> inParking = new HashMap<>();

        while (current.isBefore(end) || current.isEqual(end)) {
            current = current.plusMinutes(5);

            // Вероятности
            int probability = rnd.nextInt(34);
            int probabilityOut = rnd.nextInt(34);

            // Въезд на парковку с вероятностью 3%
            if (probability < 3) {
                if (parkedCars.size() < 20) {
                    Car car = allCars.get(rnd.nextInt(allCars.size()));
                    parkedCars.add(car.getNumber());
                    car.changeState("onParking");
//                    inParking.put(car.getNumber(), car);
                    allCars.remove(car);

                    // Добавляем запись о въезде в журнал
                    journal.putIfAbsent(car , new ArrayList<>());
                    journal.get(car).add("Въезд: " + current.format(formatter));

                    System.out.println("Парковка: " + car + " время: " + current.format(formatter));
                } else {
                    System.out.println("В парковке нет свободных мест. Приходите позже через 5 минут.");
                }
            }

            // Выезд с парковки с вероятностью 3%
            if (probabilityOut < 3 && !parkedCars.isEmpty()) {

                Parking record = journal.get();

                List<String> keys = new ArrayList<>(parkedCars);
                String randomKey = keys.get(rnd.nextInt(keys.size()));

                Car car = parkedCars.get(randomKey);
                car.changeState("onRoute");
                allCars.add(car);
                parkedCars.remove(randomKey);

                // Добавляем запись о выезде в журнал
                journal.get(car).add("Выезд: " + current.format(formatter));

                System.out.println("Возвращается на маршрут: " + car + " время: " + current.format(formatter));
            }
        }

        // Вывод журнала по окончании цикла
        System.out.println("\nЖурнал въездов и выездов:");
        for (Map.Entry<Car, List<String>> entry : journal.entrySet()) {
            Car car = entry.getKey();
            List<String> events = entry.getValue();
            System.out.println(car + " - События: " + events);
        }
    }
    public void countTimeParking (Map<Car, List<String>> journal, int bankParking) {
        journal.forEach(j -> bankParking+10);
    }
}
