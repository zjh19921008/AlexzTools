package format;

public class BooleanArr {

    private static final boolean[] IS_QUERY_RELAXED = new boolean[128];

    public static void main(String[] args) {
        /*boolean ba = IS_QUERY_RELAXED[126];
        System.out.println(ba);
        char a = 13, b = 10, c=63,d=64,e=47,f=9;
        String s = new String(new char[]{a,b,c,d,e,f});
        System.out.println(s);*/
        for (int i = 1; i < 200; i++)
            System.out.print((char) i + " ");
    }
}
