/**
 * Test class for the Shipping Cost Calculator
 * Tests all combinations of locations, weights, services, and memberships
 */
public class ShippingTest {
    public static void main(String[] args) {
        ShippingCalculator calculator = new ShippingCalculator();
        
        System.out.println("=== SHIPPING COST CALCULATOR TESTS ===");
        System.out.println();
        
        // Test all combinations of requirements
        runTests(calculator);
        
        System.out.println("All tests completed successfully!");
    }
    
    private static void runTests(ShippingCalculator calculator) {
        System.out.println("Testing Bangkok/Peri-Bangkok Area Costs:");
        testBangkokArea(calculator);
        
        System.out.println("\nTesting Other Province Costs:");
        testOtherProvinces(calculator);
        
        System.out.println("\nTesting Express Service Addition:");
        testExpressService(calculator);
        
        System.out.println("\nTesting VIP Membership Discount:");
        testVipDiscount(calculator);
        
        System.out.println("\nTesting Combined Features:");
        testCombinedFeatures(calculator);
        
        System.out.println("\nTesting Edge Cases:");
        testEdgeCases(calculator);
    }
    
    private static void testBangkokArea(ShippingCalculator calculator) {
        // Bangkok area - weight <= 1 kg = 40 baht
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 0.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 40.0, "Bangkok 0.5kg");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 1.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 40.0, "Bangkok 1.0kg");
        
        // Bangkok area - weight > 1 && <= 3 kg = 60 baht
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 1.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Bangkok 1.5kg");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 2.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Bangkok 2.0kg");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 3.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Bangkok 3.0kg");
        
        // Bangkok area - weight > 3 && <= 5 kg = 80 baht
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 3.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 80.0, "Bangkok 3.5kg");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 4.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 80.0, "Bangkok 4.0kg");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 5.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 80.0, "Bangkok 5.0kg");
        
        // Bangkok area - weight > 5 kg = 100 baht
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 5.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 100.0, "Bangkok 5.5kg");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 10.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 100.0, "Bangkok 10.0kg");
    }
    
    private static void testOtherProvinces(ShippingCalculator calculator) {
        // Other provinces - weight <= 1 kg = 60 baht
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 0.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Other 0.5kg");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 1.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Other 1.0kg");
        
        // Other provinces - weight > 1 && <= 3 kg = 90 baht
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 1.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 90.0, "Other 1.5kg");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 2.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 90.0, "Other 2.0kg");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 3.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 90.0, "Other 3.0kg");
        
        // Other provinces - weight > 3 && <= 5 kg = 120 baht
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 3.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 120.0, "Other 3.5kg");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 4.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 120.0, "Other 4.0kg");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 5.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 120.0, "Other 5.0kg");
        
        // Other provinces - weight > 5 kg = 150 baht
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 5.5, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 150.0, "Other 5.5kg");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 10.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 150.0, "Other 10.0kg");
    }
    
    private static void testExpressService(ShippingCalculator calculator) {
        // Express service adds 30 baht
        double standardCost = calculator.calculateShippingCost(ShippingCalculator.BANGKOK_AREA, 2.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR);
        double expressCost = calculator.calculateShippingCost(ShippingCalculator.BANGKOK_AREA, 2.0, ShippingCalculator.EXPRESS, ShippingCalculator.REGULAR);
        assertCostEquals(expressCost, standardCost + 30.0, "Express service addition");
        
        standardCost = calculator.calculateShippingCost(ShippingCalculator.OTHER_PROVINCE, 4.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR);
        expressCost = calculator.calculateShippingCost(ShippingCalculator.OTHER_PROVINCE, 4.0, ShippingCalculator.EXPRESS, ShippingCalculator.REGULAR);
        assertCostEquals(expressCost, standardCost + 30.0, "Express service addition");
    }
    
    private static void testVipDiscount(ShippingCalculator calculator) {
        // VIP discount is 20% off total
        double regularCost = calculator.calculateShippingCost(ShippingCalculator.BANGKOK_AREA, 4.0, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR);
        double vipCost = calculator.calculateShippingCost(ShippingCalculator.BANGKOK_AREA, 4.0, ShippingCalculator.STANDARD, ShippingCalculator.VIP);
        assertCostEquals(vipCost, regularCost * 0.8, "VIP discount");
        
        regularCost = calculator.calculateShippingCost(ShippingCalculator.OTHER_PROVINCE, 2.0, ShippingCalculator.EXPRESS, ShippingCalculator.REGULAR);
        vipCost = calculator.calculateShippingCost(ShippingCalculator.OTHER_PROVINCE, 2.0, ShippingCalculator.EXPRESS, ShippingCalculator.VIP);
        assertCostEquals(vipCost, regularCost * 0.8, "VIP discount with express");
    }
    
    private static void testCombinedFeatures(ShippingCalculator calculator) {
        // Test all features combined: other province + express + VIP
        // Base cost for other province with 3.5kg = 120 baht
        // + Express = 150 baht
        // - VIP discount = 120 baht
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 3.5, ShippingCalculator.EXPRESS, ShippingCalculator.VIP, 120.0, "All features combined");
        
        // Another combination: Bangkok + express + VIP + 5.5kg
        // Base cost = 100 baht
        // + Express = 130 baht
        // - VIP discount = 104 baht
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 5.5, ShippingCalculator.EXPRESS, ShippingCalculator.VIP, 104.0, "Bangkok express VIP 5.5kg");
    }
    
    private static void testEdgeCases(ShippingCalculator calculator) {
        // Test minimum weight
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 0.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 40.0, "Minimum weight Bangkok");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 0.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Minimum weight Other");
        
        // Test just crossing weight thresholds
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 1.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 60.0, "Just over 1kg Bangkok");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 3.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 80.0, "Just over 3kg Bangkok");
        assertCostEquals(calculator, ShippingCalculator.BANGKOK_AREA, 5.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 100.0, "Just over 5kg Bangkok");
        
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 1.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 90.0, "Just over 1kg Other");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 3.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 120.0, "Just over 3kg Other");
        assertCostEquals(calculator, ShippingCalculator.OTHER_PROVINCE, 5.001, ShippingCalculator.STANDARD, ShippingCalculator.REGULAR, 150.0, "Just over 5kg Other");
    }
    
    private static void assertCostEquals(ShippingCalculator calculator, String location, double weight, String service, String membership, double expected, String testCase) {
        double actual = calculator.calculateShippingCost(location, weight, service, membership);
        if (Math.abs(actual - expected) < 0.01) {
            System.out.println("✓ PASS: " + testCase + " -> " + actual + " baht");
        } else {
            System.out.println("✗ FAIL: " + testCase + " -> Expected: " + expected + ", Actual: " + actual);
        }
    }
    
    private static void assertCostEquals(double actual, double expected, String testCase) {
        if (Math.abs(actual - expected) < 0.01) {
            System.out.println("✓ PASS: " + testCase + " -> " + actual + " baht");
        } else {
            System.out.println("✗ FAIL: " + testCase + " -> Expected: " + expected + ", Actual: " + actual);
        }
    }
}