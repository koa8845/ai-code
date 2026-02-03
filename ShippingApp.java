import java.util.Scanner;

/**
 * Main application to demonstrate the Shipping Cost Calculator
 */
public class ShippingApp {
    public static void main(String[] args) {
        ShippingCalculator calculator = new ShippingCalculator();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SHIPPING COST CALCULATOR ===");
        System.out.println("Welcome to our shipping cost calculation system!");
        System.out.println();
        
        try {
            // Get user input
            String location = getLocationInput(scanner);
            double weight = getWeightInput(scanner);
            String serviceType = getServiceTypeInput(scanner);
            String membership = getMembershipInput(scanner);
            
            // Calculate and display results
            System.out.println();
            calculator.displayCostBreakdown(location, weight, serviceType, membership);
            
            double cost = calculator.calculateShippingCost(location, weight, serviceType, membership);
            System.out.println("Total Shipping Cost: " + cost + " baht");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static String getLocationInput(Scanner scanner) {
        System.out.println("Select destination:");
        System.out.println("1. Bangkok Area (includes peri-Bangkok)");
        System.out.println("2. Other Province");
        System.out.print("Enter choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice == 1) {
            return ShippingCalculator.BANGKOK_AREA;
        } else if (choice == 2) {
            return ShippingCalculator.OTHER_PROVINCE;
        } else {
            throw new IllegalArgumentException("Invalid choice. Please enter 1 or 2.");
        }
    }
    
    private static double getWeightInput(Scanner scanner) {
        System.out.print("Enter package weight (kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        
        return weight;
    }
    
    private static String getServiceTypeInput(Scanner scanner) {
        System.out.println("Select service type:");
        System.out.println("1. Standard Delivery");
        System.out.println("2. Express Delivery (+30 baht)");
        System.out.print("Enter choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice == 1) {
            return ShippingCalculator.STANDARD;
        } else if (choice == 2) {
            return ShippingCalculator.EXPRESS;
        } else {
            throw new IllegalArgumentException("Invalid choice. Please enter 1 or 2.");
        }
    }
    
    private static String getMembershipInput(Scanner scanner) {
        System.out.println("Select membership type:");
        System.out.println("1. Regular Customer");
        System.out.println("2. VIP Member (20% discount)");
        System.out.print("Enter choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice == 1) {
            return ShippingCalculator.REGULAR;
        } else if (choice == 2) {
            return ShippingCalculator.VIP;
        } else {
            throw new IllegalArgumentException("Invalid choice. Please enter 1 or 2.");
        }
    }
}