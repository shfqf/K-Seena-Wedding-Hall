import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

public abstract class Packageee extends Booking 
{
    protected char typeHall;
    protected int capacity;
    protected ArrayList<Integer> addOnOptions = new ArrayList<>();

    public Packageee(LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime, char typeHall, int capacity)
    {
        super(eventDate, eventStartTime, eventEndTime);
        this.typeHall = typeHall;
        this.capacity = capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public void setAddOnOptions(ArrayList<Integer> addOnOptions) 
    {
        this.addOnOptions = addOnOptions;
    }

    public double getAddOnOptionPrice()
    {
        double cost = 0;
        for (int option : addOnOptions) 
        {
            switch (option) 
            {
                case 1:
                    cost += 100;
                    break;
                case 2:
                    cost += 150;
                    break;
                case 3:
                    cost += 100;
                    break;
                default:
                    break;
            }
        }
        return cost;
    }

    // Metode abstrak yang akan diimplementasikan oleh subclass untuk menghitung harga paket
    protected abstract double calcPackagePrice();
    
    public double calcPrice() 
    {
        double basePrice = calcPackagePrice(); // Mendapatkan harga yg dikira didalam subclass
        double durationCharge = super.calcDurationCharge(); // Meendapatkan harga yg dikira didalam superclass
        double addOnPrice = getAddOnOptionPrice();
        return basePrice + durationCharge + getAddOnOptionPrice();
    }
}