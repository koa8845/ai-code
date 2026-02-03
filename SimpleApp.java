package com.example;

/**
 * โปรแกรมสำหรับแสดงการประกาศตัวแปรแบบต่างๆ ในภาษา Java
 * Variable Declaration Examples
 */
public class SimpleApp {
    public static void main(String[] args) {
        System.out.println("=== การประกาศตัวแปรแบบต่างๆ ใน Java ===\n");
        
        // 1. ตัวแปรประเภทจำนวนเต็ม (Integer Types)
        System.out.println("1. ตัวแปรประเภทจำนวนเต็ม:");
        byte byteVar = 127;                    // 8-bit (-128 ถึง 127)
        short shortVar = 32000;                // 16-bit (-32,768 ถึง 32,767)
        int intVar = 2147483647;               // 32-bit (-2^31 ถึง 2^31-1)
        long longVar = 9223372036854775807L;   // 64-bit (-2^63 ถึง 2^63-1)
        
        System.out.println("   byte: " + byteVar);
        System.out.println("   short: " + shortVar);
        System.out.println("   int: " + intVar);
        System.out.println("   long: " + longVar + "\n");
        
        // 2. ตัวแปรประเภททศนิยม (Floating-Point Types)
        System.out.println("2. ตัวแปรประเภททศนิยม:");
        float floatVar = 3.14159f;             // 32-bit (ความแม่นยำ 6-7 ตำแหน่ง)
        double doubleVar = 3.141592653589793;  // 64-bit (ความแม่นยำ 15 ตำแหน่ง)
        
        System.out.println("   float: " + floatVar);
        System.out.println("   double: " + doubleVar + "\n");
        
        // 3. ตัวแปรประเภทตัวอักษร (Character Type)
        System.out.println("3. ตัวแปรประเภทตัวอักษร:");
        char charVar = 'A';                    // 16-bit Unicode character
        char thaiChar = 'ก';
        char unicodeChar = '\u0E01';           // Unicode สำหรับ 'ก'
        
        System.out.println("   char (English): " + charVar);
        System.out.println("   char (Thai): " + thaiChar);
        System.out.println("   char (Unicode): " + unicodeChar + "\n");
        
        // 4. ตัวแปรประเภทบูลีน (Boolean Type)
        System.out.println("4. ตัวแปรประเภทบูลีน:");
        boolean boolTrue = true;
        boolean boolFalse = false;
        
        System.out.println("   boolean (true): " + boolTrue);
        System.out.println("   boolean (false): " + boolFalse + "\n");
        
        // 5. ตัวแปรประเภท String (Reference Type)
        System.out.println("5. ตัวแปรประเภท String:");
        String stringVar = "สวัสดีครับ";
        String emptyString = "";
        String nullString = null;
        
        System.out.println("   String: " + stringVar);
        System.out.println("   Empty String: \"" + emptyString + "\"");
        System.out.println("   Null String: " + nullString + "\n");
        
        // 6. การประกาศตัวแปรแบบ final (Constants)
        System.out.println("6. การประกาศค่าคงที่ (final):");
        final double PI = 3.141592653589793;
        final String UNIVERSITY = "มหาวิทยาลัยราชภัฏนครปฐม";
        
        System.out.println("   final PI: " + PI);
        System.out.println("   final UNIVERSITY: " + UNIVERSITY + "\n");
        
        // 7. การประกาศตัวแปรหลายตัวในบรรทัดเดียว
        System.out.println("7. การประกาศหลายตัวแปรในบรรทัดเดียว:");
        int x = 10, y = 20, z = 30;
        String firstName = "สมชาย", lastName = "ใจดี";
        
        System.out.println("   x = " + x + ", y = " + y + ", z = " + z);
        System.out.println("   ชื่อ: " + firstName + " " + lastName + "\n");
        
        // 8. ตัวแปรประเภท Array (อาร์เรย์)
        System.out.println("8. ตัวแปรประเภท Array:");
        int[] numbers = {1, 2, 3, 4, 5};
        String[] fruits = {"แอปเปิล", "กล้วย", "ส้ม"};
        
        System.out.print("   int[]: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.print("   String[]: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println("\n");
        
        // 9. การประกาศตัวแปรโดยไม่กำหนดค่าเริ่มต้น
        System.out.println("9. การประกาศตัวแปรโดยไม่กำหนดค่าเริ่มต้น:");
        int uninitializedInt;
        String uninitializedString;
        
        uninitializedInt = 100;
        uninitializedString = "กำหนดค่าทีหลัง";
        
        System.out.println("   int (กำหนดค่าทีหลัง): " + uninitializedInt);
        System.out.println("   String (กำหนดค่าทีหลัง): " + uninitializedString + "\n");
        
        // 10. ตัวแปรประเภท var (Type Inference - Java 10+)
        System.out.println("10. การประกาศตัวแปรด้วย var (Type Inference):");
        var autoInt = 42;                      // Java จะรู้เองว่าเป็น int
        var autoString = "สวัสดี";             // Java จะรู้เองว่าเป็น String
        var autoDouble = 3.14;                 // Java จะรู้เองว่าเป็น double
        
        System.out.println("   var (int): " + autoInt);
        System.out.println("   var (String): " + autoString);
        System.out.println("   var (double): " + autoDouble + "\n");
        
        System.out.println("=== จบการแสดงตัวอย่าง ===");
    }
}
