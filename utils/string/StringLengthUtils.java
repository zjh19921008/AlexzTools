package string;

import java.nio.charset.StandardCharsets;

public class StringLengthUtils {

    public static Integer charLength(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        return length;
    }

    public static void main(String[] args) {
        System.out.println(charLength("渣打银行(中国)有限公司上海分行测试测试测试"));
    }

}
