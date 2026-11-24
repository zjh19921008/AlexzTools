package string;

import java.util.Base64;
import java.util.Scanner;

public class Base64Utils {

    public static String encode(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes());
    }

    public static String decode(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You need encode(e) or decode(d) ? ");
        String tag = scanner.nextLine();
        System.out.println("Enter Base64 String:");
        String str = scanner.nextLine();
        if (tag.equals("d")) {
            System.out.println("Decoded String: \r\n" + decode(str));
        } else if (tag.equals("e")) {
            System.out.println("Encoded String: \r\n" + encode(str));
        }
    }
}
