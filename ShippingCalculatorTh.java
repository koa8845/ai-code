/**
 * ระบบคำนวณค่าจัดส่งสินค้า
 * คำนวณค่าจัดส่งตามสถานที่ปลายทาง น้ำหนัก ประเภทบริการ และสถานะสมาชิก
 */
public class ShippingCalculatorTh {
    
    // ค่าคงที่สำหรับสถานที่
    public static final String BANGKOK_AREA = "BANGKOK";
    public static final String OTHER_PROVINCE = "OTHER";
    
    // ค่าคงที่สำหรับประเภทบริการ
    public static final String STANDARD = "STANDARD";
    public static final String EXPRESS = "EXPRESS";
    
    // ค่าคงที่สำหรับสถานะสมาชิก
    public static final String REGULAR = "REGULAR";
    public static final String VIP = "VIP";
    
    /**
     * คำนวณค่าจัดส่งตามพารามิเตอร์ทั้งหมด
     * @param location สถานที่ปลายทาง (BANGKOK หรือ OTHER)
     * @param weight น้ำหนักพัสดุเป็นกิโลกรัม
     * @param serviceType ประเภทบริการ (STANDARD หรือ EXPRESS)
     * @param membership สถานะสมาชิก (REGULAR หรือ VIP)
     * @return ค่าจัดส่งที่คำนวณได้
     */
    public double calculateShippingCost(String location, double weight, String serviceType, String membership) {
        // ตรวจสอบข้อมูลนำเข้า
        if (!isValidLocation(location)) {
            throw new IllegalArgumentException("สถานที่ไม่ถูกต้อง ใช้ BANGKOK หรือ OTHER");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("น้ำหนักต้องมากกว่า 0");
        }
        if (!isValidServiceType(serviceType)) {
            throw new IllegalArgumentException("ประเภทบริการไม่ถูกต้อง ใช้ STANDARD หรือ EXPRESS");
        }
        if (!isValidMembership(membership)) {
            throw new IllegalArgumentException("สถานะสมาชิกไม่ถูกต้อง ใช้ REGULAR หรือ VIP");
        }
        
        // คำนวณต้นทุนพื้นฐานตามสถานที่และน้ำหนัก
        double baseCost = calculateBaseCost(location, weight);
        
        // เพิ่มค่าบริการด่วนถ้าจำเป็น
        if (EXPRESS.equals(serviceType)) {
            baseCost += 30.0;
        }
        
        // ปรับส่วนลด VIP ถ้าจำเป็น
        if (VIP.equals(membership)) {
            baseCost *= 0.8; // ส่วนลด 20%
        }
        
        return Math.round(baseCost * 100.0) / 100.0; // ปัดเศษเป็น 2 ตำแหน่งทศนิยม
    }
    
    /**
     * คำนวณต้นทุนค่าจัดส่งพื้นฐานตามสถานที่และน้ำหนัก
     */
    private double calculateBaseCost(String location, double weight) {
        if (BANGKOK_AREA.equals(location)) {
            return getBangkokAreaCost(weight);
        } else {
            return getOtherProvinceCost(weight);
        }
    }
    
    /**
     * รับค่าจัดส่งสำหรับพื้นที่กรุงเทพฯ และปริมณฑล
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
     * รับค่าจัดส่งสำหรับต่างจังหวัด
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
    
    // เมทอดตรวจสอบ
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
     * แสดงรายละเอียดค่าจัดส่ง
     */
    public void displayCostBreakdown(String location, double weight, String serviceType, String membership) {
        System.out.println("=== รายละเอียดค่าจัดส่ง ===");
        System.out.println("สถานที่ปลายทาง: " + (BANGKOK_AREA.equals(location) ? "พื้นที่กรุงเทพฯ" : "ต่างจังหวัด"));
        System.out.println("น้ำหนัก: " + weight + " กก.");
        System.out.println("ประเภทบริการ: " + (STANDARD.equals(serviceType) ? "มาตรฐาน" : "ด่วน"));
        System.out.println("สถานะสมาชิก: " + (REGULAR.equals(membership) ? "ทั่วไป" : "VIP"));
        System.out.println("------------------------");
        
        double baseCost = calculateBaseCost(location, weight);
        System.out.println("ค่าจัดส่งพื้นฐาน: " + baseCost + " บาท");
        
        if (EXPRESS.equals(serviceType)) {
            System.out.println("ค่าบริการด่วน: 30.00 บาท");
        }
        
        double subtotal = calculateBaseCost(location, weight);
        if (EXPRESS.equals(serviceType)) {
            subtotal += 30.0;
        }
        System.out.println("รวมก่อนส่วนลด: " + subtotal + " บาท");
        
        if (VIP.equals(membership)) {
            double discount = subtotal * 0.2;
            System.out.println("ส่วนลดสมาชิก VIP (20%): -" + String.format("%.2f", discount) + " บาท");
        }
        
        double finalCost = calculateShippingCost(location, weight, serviceType, membership);
        System.out.println("ค่าจัดส่งสุทธิ: " + finalCost + " บาท");
        System.out.println("========================");
    }
}