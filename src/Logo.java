import java.util.Random;

public enum Logo {
    LEXUS, MERCEDES, BMW, HYUNDAY, KIA, 世界名车, SUBARU;
    private static Random rnd = new Random();

    static Logo random(){
        Logo[] values = Logo.values();
        int selected = rnd.nextInt(values.length);
        return values[selected];
    }
}
