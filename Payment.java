import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Payment extends Booking
{
    //attribute
    private String paymentId;
    private String bankDetails;
    private boolean paymentStatus;
    private String paymentMethod;
    private double amount;
    private int partialPaymentCount;
    private Packageee newPackagee;
    private List<Double> paymentHistory;

    //constructor
    public Payment(LocalDate weddingDate, LocalTime eventStartTime, LocalTime eventEndTime, String paymentMethod, double amount, Packageee newPackagee)
    {
        super(weddingDate, eventStartTime, eventEndTime);
        this.paymentId = UUID.randomUUID().toString().substring(0, 8);
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentStatus = false;
        this.partialPaymentCount = 0;
        this.newPackagee = newPackagee;
        this.paymentHistory = new ArrayList<>();
    }

    //getter
    public String getPaymentId() 
    {
        return paymentId;
    }

    public String getBankDetails() 
    {
        return bankDetails;
    }

    public boolean getPaymentStatus()
    {
        return paymentStatus;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }

    public double getAmount()
    {
        return amount;
    }

    //setter
    public void setPaymentId() 
    {
        this.paymentId = UUID.randomUUID().toString().substring(0, 8);
    }

    public void setBankDetails(String bankDetails) 
    {
        this.bankDetails = bankDetails;
    }

    public void setPaymentStatus(boolean paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public void setAmount(double amount) 
    {
        this.amount = amount;
    }

    //method 
    private void enterBankDetails() //bank details for customer
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter bank details (number account):");
        this.bankDetails = scanner.nextLine();
        System.out.println("Bank details successfully saved.");
    }

    public double calcPriceWithTax()
    {
        double priceBooking = super.calcDurationCharge();// get price from class Booking
        double pricePackage = newPackagee.calcPackagePrice();
        double tax = 0.06; // tax
        double total = priceBooking + pricePackage;
        double totalPriceWithTax = total + (total * tax);
        DecimalFormat df = new DecimalFormat("#.##"); // Format kepada dua tempat perpuluhan
        return Double.parseDouble(df.format(totalPriceWithTax));
    }

    public double calcDis() 
    {
        double totalPrice = calcPriceWithTax();
        double dis;
        if (paymentMethod.equalsIgnoreCase("Cash")) 
        {
            dis = 0.05;
        } 
        
        else
        {
            dis = 0.02;
        }
        double totalPriceAfterDis = totalPrice - (totalPrice * dis);

        DecimalFormat df = new DecimalFormat("#.##"); // Format kepada dua tempat perpuluhan

        return Double.parseDouble(df.format(totalPriceAfterDis));
    }
    
    public double partialPayment(double amount) 
    {
        double totalPriceWithDis = calcDis();
        double totalPaid = paymentHistory.stream().mapToDouble(Double::doubleValue).sum();

        if (amount > 0 && partialPaymentCount < 3) 
        {
            System.out.println("RM" + amount + " made just now.");
            paymentHistory.add(amount);
            partialPaymentCount++;
        } 
        
        else
        {
            System.out.println("Partial payment failed. Maximum of 3 payments allowed.");
        }

        double remainingPayment = totalPriceWithDis - totalPaid - amount;

        if (remainingPayment <= 0) 
        {
            paymentStatus = true;
            remainingPayment = 0;
        }
        return remainingPayment;
    }

    public String generatePaymentTable() 
    {
    StringBuilder table = new StringBuilder();
    table.append("+----------------------------------------+\n");
    table.append("|             RECEIPT OF PAYMENT         |\n");
    table.append("+----------------------------------------+\n");
    table.append(String.format("| %-15s: %-21s |\n", "Payment ID", paymentId));
    table.append(String.format("| %-15s: %-21s |\n", "Payment Method", paymentMethod));
    table.append(String.format("| %-15s: %-21s |\n", "Bank Details", bankDetails != null ? bankDetails : "N/A"));
    table.append(String.format("| %-15s: %-21s |\n", "Payment Status", paymentStatus ? "Paid" : "Unpaid"));
    table.append("+----------------------------------------+\n");
    table.append("|           HISTORY OF PAYMENTS          |\n");
    table.append("+----------------------------------------+\n");

    if (paymentHistory.isEmpty()) 
    {
        table.append("| No payments made yet                   |\n");
    } 
    else
    {
        for (int i = 0; i < paymentHistory.size(); i++) 
        {
            table.append(String.format("| Payment %d: RM %-22.2f  |\n", i + 1, paymentHistory.get(i)));
        }
    }

    table.append("+----------------------------------------+\n");
    return table.toString();
    }



    @Override
    public String toString() 
    {
        return generatePaymentTable();
               
    }


    
}