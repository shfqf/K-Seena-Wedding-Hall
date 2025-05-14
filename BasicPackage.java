import java.time.LocalDate;
import java.time.LocalTime;

public class BasicPackage extends Packageee //subclass dari class packageee
{
    public BasicPackage(LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime, char typeHall, int capacity) 
    {
        super(eventDate, eventStartTime, eventEndTime, typeHall, capacity);
    }

    @Override
    protected double calcPackagePrice() 
    {
        double packagePrice = 15500; // Harga dasar untuk Basic Package
        double additionalCost = 0;

        // Menghitung biaya tambahan berdasarkan kapasitas
        if (capacity > 300) 
        {
            additionalCost = (capacity - 300) * 2.50;
        }

        // Menghitung harga akhir
        return packagePrice + additionalCost ;
    }
}