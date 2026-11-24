package format;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CodeTest {

    public static void main(String[] args) {
        String txt = "æˆ�åŠŸ";
        Charset gbk = Charset.forName("GBK");
        String s = new String(txt.getBytes(gbk), StandardCharsets.UTF_8);
        System.out.println("GBK:"+s);
        String s1 = new String(txt.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println("ISO:"+s1);
        String s2 = new String(txt.getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8);
        System.out.println("USACII:"+s2);
        String s3 = new String(txt.getBytes(StandardCharsets.UTF_8), gbk);
        System.out.println("2gbk:" + s3);
        String s4 = new String(txt.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        System.out.println("ISO:"+s4);

    }
}
