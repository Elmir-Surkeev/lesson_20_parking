import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Action {
    public static void main() {
        List<String> allCars = new ArrayList<String>();

        for (int i=1; i<=20; i++){
            String logo = Logo.random().toString();
            allCars.add(String.valueOf(new Car(i, logo, "onRoute", "124")));
        }



        //Вывод всех машин в городе 20-нужно исправить на 200
        allCars.forEach(System.out::println);
    }
}
