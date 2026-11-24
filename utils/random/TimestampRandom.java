package random;

public class TimestampRandom {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        long x = l * l;
        System.out.println(x);
        System.out.println(String.valueOf(x).length());
    }
}
