public class Main {
    public static void main(String[] args) {
        Action action = new Action();
        action.createCars(200);
        action.simulateParking();
        action.printJournal();
        System.out.println("Общий капитал парковки: " + action.bankParking + " центов");
    }
}