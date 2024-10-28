import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Action {
    public static void main() {
        Random rnd = new Random();
        List<Car> allCars = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Map<String, Car> inParking = new HashMap<>();

        for (int i = 1; i <= 200; i++) {
            LocalDateTime now = LocalDateTime.now();
            int probability = rnd.nextInt(100);

            // Автомобили, которые едут по городу
            String logo = Logo.random().toString();
            Car car = new Car(i, logo, "onRoute", "124");
            allCars.add(car);

            // На парковке с вероятностью 3%
            if (probability == 3) {
                car.changeState("onParking");
                inParking.put(now.format(formatter), car);
          //      System.out.println(inParking);
            }
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusDays(30);
        LocalDateTime current = now;


        while(current.isBefore(end) || current.isEqual(end)) {
            System.out.println(current.format(formatter));
            current = current.plusMinutes(5);
        }

        //allCars.forEach(System.out::println);
    }
}
