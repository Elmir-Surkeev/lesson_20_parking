import java.util.*;

public class Action {
    public static void main() {
        Random rnd = new Random();
        List<Car> allCars = new ArrayList<Car>();

        //Использовал Map так как поддерживает несколько типов данных
        Map<Integer, Car> inParking = new HashMap<>();
        for (int i=1; i<=200; i++){
            int probability = rnd.nextInt(100);

            //едут по городу
            String logo = Logo.random().toString();
            Car car = new Car(i, logo, "onRoute", "124");
            allCars.add(car);

            //на парковке с 3% вероятностью
            if (probability == 3){
                car = new Car(i, logo, "onParking", "124");
                inParking.put(1, car);
                allCars.set(i-1, car);
                System.out.println(inParking);
            }

        }

        //Вывод всех машин в городе 20-нужно исправить на 200
        allCars.forEach(System.out::println);
    }
}
