import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingRecord {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ParkingRecord(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long calculateParkingTime() {
        if (endTime == null) {
            return 0;
        }
        long minuteParked = Duration.between(startTime, endTime).toMinutes();
        long intervals = minuteParked/5;
        return intervals;
    }
}
