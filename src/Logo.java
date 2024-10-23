import java.util.Random;

public enum Logo {
    Lexus, Mercedes, BMW, Hyundai, Kia, 世界名车, Subaru;
    private static Random rnd = new Random();

    static Logo random(){
        Logo[] values = Logo.values();
        int selected = rnd.nextInt(values.length);
        return values[selected];
    }
}
