import java.util.Scanner;

public class CRC {
    // Method to perform XOR operation
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // Method to perform CRC calculation
    public static String calculateCRC(String data, String divisor) {
        // Append zeroes to the data
        String paddedData = data + "0".repeat(divisor.length() - 1);
        
        // Perform the division
        String remainder = paddedData;
        for (int i = 0; i <= paddedData.length() - divisor.length(); i++) {
            if (remainder.charAt(i) == '1') {
                String tempDivisor = divisor;
                for (int j = 0; j < tempDivisor.length(); j++) {
                    remainder = remainder.substring(0, i + j) + 
                                xor(String.valueOf(remainder.charAt(i + j)), String.valueOf(tempDivisor.charAt(j))) + 
                                remainder.substring(i + j + 1);
                }
            }
        }

        // The remainder is the CRC
        return remainder.substring(remainder.length() - (divisor.length() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data and divisor
        System.out.print("Enter the data (binary string): ");
        String data = scanner.nextLine();
        System.out.print("Enter the divisor (binary string): ");
        String divisor = scanner.nextLine();

        // Calculate CRC
        String crc = calculateCRC(data, divisor);
        System.out.println("CRC: " + crc);

        // Final codeword
        String codeword = data + crc;
        System.out.println("Codeword: " + codeword);
        
        scanner.close();
    }
}
