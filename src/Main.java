import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Action action = new Action();
        action.createCars(200);
        action.simulateParking();
        action.printJournal();
        LocalDate targetDate = LocalDate.of(2024, 11, 10);
        long dailyEarnings = action.calculateMoneyForOneDay(targetDate);
        System.out.println(dailyEarnings);
        System.out.println("Общий капитал парковки: " + action.bankParking + " центов");
    }
}