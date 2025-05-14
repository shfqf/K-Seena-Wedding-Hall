import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main 
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to K'Seena Wedding Hall Booking System");

        Scanner scanner = new Scanner(System.in);

        //CLASS CUSTOMER
        System.out.print("Enter customer name: ");
        String custName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        while (!email.contains("@")) 
        {
            System.out.println("Invalid email. Try again.");
            System.out.print("Enter customer email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter customer phone number: ");
        String phoneNum = scanner.nextLine();
        while (!phoneNum.matches("\\d{10}")) 
        {
            System.out.println("Invalid phone number. Try again.");
            System.out.print("Enter customer phone number: ");
            phoneNum = scanner.nextLine();
        }

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        //Create object payment
        Customer customer = new Customer(custName, email, phoneNum, address);

        clearConsole();

        //CLASS BOOKING
        System.out.print("\nEnter the event date (e.g., 01-01-2024): ");
        String dateInput = scanner.next();
        LocalDate eventDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.print("Enter the event start time (e.g., 14:00): ");
        String startTimeInput = scanner.next();
        LocalTime eventStartTime = LocalTime.parse(startTimeInput);

        System.out.print("Enter the event end time (e.g., 18:00): ");
        String endTimeInput = scanner.next();
        LocalTime eventEndTime = LocalTime.parse(endTimeInput);
        
        //Craete object booking
        Booking booking = new Booking(eventDate, eventStartTime, eventEndTime);

        booking.displayDuration();
        clearConsole();

        //CLASS PACKAGE
        System.out.println("------------------------------------");
        System.out.println("Package Options:");
        System.out.println("1. Promotion Package");
        System.out.println("2. Basic Package");
        System.out.println("3. Premium Package");
        System.out.print("Choose a package (1-3): ");

        int packageOption = scanner.nextInt();
        scanner.nextLine();

        char typeHall = 'A';
        switch (packageOption) 
        {
            case 1:
                System.out.println("------------------------------------");
                System.out.println("Promotion Package includes...");
                typeHall = 'A';
                break;
            case 2:
                System.out.println("------------------------------------");
                System.out.println("Basic Package includes...");
                typeHall = 'B';
                break;
            case 3:
                System.out.println("------------------------------------");
                System.out.println("Premium Package includes...");
                typeHall = 'C';
                break;
            default:
                System.out.println("Invalid option. Exiting.");
                scanner.close();
                return;
        }

        System.out.print("Enter the capacity of the event: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("------------------------------------");
        System.out.println("Hall Options:");
        System.out.println("A. Gamuda Garden");
        System.out.println("B. Eco Palladium");
        System.out.println("C. Grand Hall");
        System.out.print("Choose a hall (A-C): ");

        char selectedHall = scanner.nextLine().toUpperCase().charAt(0);
        if (selectedHall < 'A' || selectedHall > 'C') 
        {
            System.out.println("Invalid hall choice. Exiting.");
            scanner.close();
            return;
        }

        ArrayList<Integer> addOnOptions = new ArrayList<>();
        while (true) 
        {
            System.out.println("------------------------------------");
            System.out.println("Add-on Options:");
            System.out.println("1. MUA (Makeup Artist)");
            System.out.println("2. Gift");
            System.out.println("3. Wedding dress");
            System.out.println("4. No add-on");
            System.out.println("5. Move to the next");
            System.out.print("Choose an add-on option (1-4): ");

            int addOnOption = scanner.nextInt();
            scanner.nextLine();

            if (addOnOption == 5) 
            {
                break;
            } 
            
            else if (addOnOption >= 1 && addOnOption <= 3) 
            {
                addOnOptions.add(addOnOption);
            } 
            
            else 
            {
                System.out.println("Invalid option. Please choose again.");
            }
        }

        Packageee weddingPackage;
        switch (packageOption) 
        {
            case 1:
                weddingPackage = new PromotionPackage(eventDate, eventStartTime, eventEndTime, typeHall, capacity);
                break;
            case 2:
                weddingPackage = new BasicPackage(eventDate, eventStartTime, eventEndTime, typeHall, capacity);
                break;
            case 3:
                weddingPackage = new PremiumPackage(eventDate, eventStartTime, eventEndTime, typeHall, capacity);
                break;
            default:
                System.out.println("Invalid option. Exiting.");
                scanner.close();
                return;
        }

        weddingPackage.setCapacity(capacity);
        weddingPackage.setAddOnOptions(addOnOptions);

        clearConsole();

        System.out.println("------------------------------------");
        System.out.println("\nCustomer details:");
        System.out.println(customer);
        System.out.println("Selected Package: " + (weddingPackage instanceof PromotionPackage ? "Promotion Package" : weddingPackage instanceof BasicPackage ? "Basic Package" : "Premium Package"));
        System.out.println("Selected Hall: " + selectedHall);
        System.out.println("Event Capacity: " + capacity);
        System.out.print("Add-on Options: ");
        for (int option : addOnOptions) 
        {
            switch (option) 
            {
                case 1:
                    System.out.print("MUA, ");
                    break;
                case 2:
                    System.out.print("Gift, ");
                    break;
                case 3:
                    System.out.print("Wedding dress, ");
                    break;
            }
        }
        
        if (addOnOptions.isEmpty()) 
        {
            System.out.print("No add-on");
        }
        System.out.println();

        double total = weddingPackage.calcPrice();
        System.out.println("\nTotal Package Price : RM " + total);
        System.out.println("\nPress any key to continue with payment.");
        System.out.println("------------------------------------");

        scanner.nextLine();
        String paymentId = UUID.randomUUID().toString().substring(0, 8);
        System.out.println("\nPayment ID: " + paymentId);

        System.out.print("Enter payment method (Cash or Card): ");
        String paymentMethod = scanner.nextLine();

        if (paymentMethod.equalsIgnoreCase("Card"))
        {
            System.out.print("Enter bank details (account number): ");
            String bankDetails = scanner.nextLine();
            System.out.println("\nYour bank details have been successfully saved.");
            System.out.println("\nCongratulations! You have successfully received a 2% discount.");
        } 
        else if (paymentMethod.equalsIgnoreCase("Cash"))
        {
            System.out.println("\nCongratulations! You have successfully received a 5% discount.");
        }
        else
        {
            System.out.println("\nInvalid payment method. Exiting.");
            scanner.close();
            return;
        }

        //Create object payment
        Payment payment = new Payment(eventDate, eventStartTime, eventEndTime, paymentMethod, total, weddingPackage);

        double totalPriceWithTax = payment.calcPriceWithTax();
        System.out.println("The final total after 6% tax: RM " + totalPriceWithTax);

        double totalPriceWithDis = payment.calcDis();
        System.out.println("The final total after discount: RM " + totalPriceWithDis);

        for (int i = 0; i < 3; i++) 
        {
            System.out.print("\nEnter the amount you want to pay: RM ");
            double amount = scanner.nextDouble();
            double remainingPayment = payment.partialPayment(amount);
            System.out.println("\nRemaining Payment: RM " + remainingPayment);

            if (remainingPayment == 0) 
            {
                System.out.println("\nPayment status: Paid");
                break;
            } 
            else 
            {
                System.out.println("\nPayment status: Pending");
            }
            
        }

        clearConsole();

        System.out.println(payment.toString());

        scanner.close();
    }

    private static void clearConsole() 
    {
        try 
        {
            if (System.getProperty("os.name").contains("Windows")) 
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
            else 
            {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } 
        catch (IOException | InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }
}