import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class Booking 
 {
    private LocalDate weddingDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    public Booking(LocalDate weddingDate, LocalTime eventStartTime, LocalTime eventEndTime) 
    {
        this.weddingDate = weddingDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    // Getters and setters (removed for brevity)

    // Method to calculate event duration
    public Duration getEventDuration() {
        return Duration.between(eventStartTime, eventEndTime);
    }

    // Method to calculate duration charge for exceeding 5 hours
    public double calcDurationCharge() 
    {
        long durationHours = getEventDuration().toHours();
        if (durationHours > 5)
        {
            return (durationHours - 5) * 7.00;
        }
        else 
        {
            return 0.00;
        }
    }
    
    // Method to display event duration
    public void displayDuration() {
        Duration duration = getEventDuration();
        System.out.println("Event duration: " + duration.toHours() + " hours " + duration.toMinutesPart() + " minutes");
        System.out.println("Duration Charge: RM " + calcDurationCharge());
    }
}