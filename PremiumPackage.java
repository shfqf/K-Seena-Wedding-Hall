import java.time.LocalDate;
import java.time.LocalTime;

public class PremiumPackage extends Packageee
{
    public PremiumPackage(LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime, char typeHall, int capacity)
    {
        super(eventDate, eventStartTime, eventEndTime, typeHall, capacity);
    }
    
    @Override
    protected double calcPackagePrice() 
    {
        double packagePrice = 18000; // Harga dasar untuk Basic Package
        double additionalCost = 0;

        // Menghitung biaya tambahan berdasarkan kapasitas
        if (capacity > 500) 
        {
            additionalCost = (capacity - 500) * 2.50;
        }

        // Menghitung harga akhir
        return packagePrice + additionalCost ;
    }
}