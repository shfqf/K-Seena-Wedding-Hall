import java.time.LocalDate;
import java.time.LocalTime;

public class PromotionPackage extends Packageee 
{
    public PromotionPackage(LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime, char typeHall, int capacity) 
    {
        super(eventDate, eventStartTime, eventEndTime, typeHall, capacity);
    }

    @Override
    protected double calcPackagePrice() 
    {
        double packagePrice = 9900; // Harga dasar untuk Basic Package
        double additionalCost = 0;

        // Menghitung biaya tambahan berdasarkan kapasitas
        if (capacity > 100) 
        {
            additionalCost = (capacity - 100) * 2.50;
        }

        // Menghitung harga akhir
        return packagePrice + additionalCost ;
    }
}