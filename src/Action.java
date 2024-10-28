import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Action {
    public static void main() {
        Random rnd = new Random();
        List<Car> allCars = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Автомобили, которые едут по городу
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
        Map<String, Car> inParking = new HashMap<>();
        while (current.isBefore(end) || current.isEqual(end)) {
            current = current.plusMinutes(5);

            // Процентная вероятность
            int probability = rnd.nextInt(34);
            int probabilityOut = rnd.nextInt(34);

            // На парковке с вероятностью 3%
            if (probability < 3) { // Если число в диапазоне 0, 1, или 2
                Car car = allCars.get(rnd.nextInt(allCars.size()));
                car.changeState("onParking");
                inParking.put(current.format(formatter), car);
                allCars.remove(car);
                System.out.println("Парковка: " + car + " время: " + current.format(formatter));
            }
            if (probabilityOut <3 && !inParking.isEmpty()){
                List<String> key = new ArrayList<>(inParking.keySet());
                String randomKey = key.get(rnd.nextInt(key.size()));

                Car car = inParking.get(randomKey);
                car.changeState("onRoute");
                allCars.add(car);
                inParking.remove(randomKey);
                System.out.println("Возвращается на маршрут: " + car + " время: " + current.format(formatter));
            }
        }

    }
}
