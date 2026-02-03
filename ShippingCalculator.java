/**
 * Shipping Cost Calculator System
 * Calculates shipping costs based on location, weight, service type, and membership
 */
public class ShippingCalculator {
    // Location constants
    public static final String BANGKOK_AREA = "BANGKOK";
    public static final String OTHER_PROVINCE = "OTHER";
    
    // Service type constants
    public static final String STANDARD = "STANDARD";
    public static final String EXPRESS = "EXPRESS";
    
    // Membership constants
    public static final String REGULAR = "REGULAR";
    public static final String VIP = "VIP";
    
    /**
     * Calculate shipping cost based on all parameters
     * @param location Destination location (BANGKOK or OTHER)
     * @param weight Package weight in kilograms
     * @param serviceType Service type (STANDARD or EXPRESS)
     * @param membership Membership type (REGULAR or VIP)
     * @return Calculated shipping cost
     */
    public double calculateShippingCost(String location, double weight, String serviceType, String membership) {
        // Validate inputs
        if (!isValidLocation(location)) {
            throw new IllegalArgumentException("Invalid location. Use BANGKOK or OTHER");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        if (!isValidServiceType(serviceType)) {
            throw new IllegalArgumentException("Invalid service type. Use STANDARD or EXPRESS");
        }
        if (!isValidMembership(membership)) {
            throw new IllegalArgumentException("Invalid membership. Use REGULAR or VIP");
        }
        
        // Calculate base cost based on location and weight
        double baseCost = calculateBaseCost(location, weight);
        
        // Add express service fee if applicable
        if (EXPRESS.equals(serviceType)) {
            baseCost += 30.0;
        }
        
        // Apply VIP discount if applicable
        if (VIP.equals(membership)) {
            baseCost *= 0.8; // 20% discount
        }
        
        return Math.round(baseCost * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    /**
     * Calculate base shipping cost based on location and weight
     */
    private double calculateBaseCost(String location, double weight) {
        if (BANGKOK_AREA.equals(location)) {
            return getBangkokAreaCost(weight);
        } else {
            return getOtherProvinceCost(weight);
        }
    }
    
    /**
     * Get shipping cost for Bangkok and peri-Bangkok area
     */
    private double getBangkokAreaCost(double weight) {
        if (weight <= 1) {
            return 40.0;
        } else if (weight <= 3) {
            return 60.0;
        } else if (weight <= 5) {
            return 80.0;
        } else {
            return 100.0;
        }
    }
    
    /**
     * Get shipping cost for other provinces
     */
    private double getOtherProvinceCost(double weight) {
        if (weight <= 1) {
            return 60.0;
        } else if (weight <= 3) {
            return 90.0;
        } else if (weight <= 5) {
            return 120.0;
        } else {
            return 150.0;
        }
    }
    
    // Validation methods
    private boolean isValidLocation(String location) {
        return BANGKOK_AREA.equals(location) || OTHER_PROVINCE.equals(location);
    }
    
    private boolean isValidServiceType(String serviceType) {
        return STANDARD.equals(serviceType) || EXPRESS.equals(serviceType);
    }
    
    private boolean isValidMembership(String membership) {
        return REGULAR.equals(membership) || VIP.equals(membership);
    }
    
    /**
     * Display shipping cost breakdown
     */
    public void displayCostBreakdown(String location, double weight, String serviceType, String membership) {
        System.out.println("=== SHIPPING COST BREAKDOWN ===");
        System.out.println("Location: " + (BANGKOK_AREA.equals(location) ? "Bangkok Area" : "Other Province"));
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Service Type: " + serviceType);
        System.out.println("Membership: " + membership);
        System.out.println("------------------------");
        
        double baseCost = calculateBaseCost(location, weight);
        System.out.println("Base Cost: " + baseCost + " baht");
        
        if (EXPRESS.equals(serviceType)) {
            System.out.println("Express Service Fee: 30.00 baht");
        }
        
        double subtotal = calculateBaseCost(location, weight);
        if (EXPRESS.equals(serviceType)) {
            subtotal += 30.0;
        }
        System.out.println("Subtotal: " + subtotal + " baht");
        
        if (VIP.equals(membership)) {
            double discount = subtotal * 0.2;
            System.out.println("VIP Discount (20%): -" + String.format("%.2f", discount) + " baht");
        }
        
        double finalCost = calculateShippingCost(location, weight, serviceType, membership);
        System.out.println("FINAL COST: " + finalCost + " baht");
        System.out.println("========================");
    }
}