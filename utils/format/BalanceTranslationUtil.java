package format;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额大小写转换
 * PS：暂时用不到厘单位，大转小保留两位小数
 */
public class BalanceTranslationUtil {

    private static final String[] fraction = { "角", "分", "厘" };
    private static final char[] cnNum = new char[]{'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    private static final String[][] unit = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
    private static final char[] unitArr = new char[]{'厘', '分', '角', '圆', '拾', '佰', '仟', '万', '亿'};

    /**
     * 数字金额大写转换
     * @param b 数字
     * @return 中文大写数字
     */
    public static String toUppercase(double b) {

        String head = b < 0 ? "负" : "";
        b = Math.abs(b);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < fraction.length; i++)
        {
            s.append((cnNum[(int) (Math.floor(b * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", ""));
        }
        if (s.length() < 1)
        {
            s = new StringBuilder("整");
        }
        int integerPart = (int) Math.floor(b);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++)
        {
            StringBuilder p = new StringBuilder();
            for (int j = 0; j < unit[1].length && b > 0; j++)
            {
                p.insert(0, cnNum[integerPart % 10] + unit[1][j]);
                integerPart = integerPart / 10;
            }
            s.insert(0, p.toString().replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]);
        }

        return head + s.toString().replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * 中文金额转数字
     * @param chineseNumber 中文金额
     * @return lowercaseNum 阿拉伯数字
     */
    public static BigDecimal toLowercase(String chineseNumber) {
        BigDecimal result = new BigDecimal(0);
        int lastUnitIndex = 0, num = 0;
        chineseNumber = chineseNumber.replace("元", "圆");
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean isUnit = true;
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnNum.length; j++) {
                // 是数字
                if (c == cnNum[j]) {
                    // 数字值 = 索引
                    num = j;
                    isUnit = false;
                    break;
                }
            }
            if (isUnit) {
                // 第一个就是单位，如：拾伍万圆整
                if (i == 0) {
                    num = 1;
                }
                int unitIndex = getUnitIndex(c);
                BigDecimal unit = getUnit(c);
                if (unitIndex > lastUnitIndex) {
                    result = result.add(new BigDecimal(num)).multiply(unit);
                } else {
                    result = result.add(new BigDecimal(num).multiply(unit));
                }
                lastUnitIndex = unitIndex;
                num = 0;
            }
        }
        return result.setScale(2, RoundingMode.DOWN);
    }

    private static int getUnitIndex(char c) {
        for (int j = 0; j < unitArr.length; j++) {
            if (c == unitArr[j]) {
                return j;
            }
        }
        return 0;
    }

    private static BigDecimal getUnit(char c) {
        double num = 0;
        int unitIndex = getUnitIndex(c);
        switch (unitIndex) {
            // '厘', '分', '角', '圆', '拾', '佰', '仟', '万', '亿'
            case 4:
                num = 10;
                break;
            case 5:
                num = 100;
                break;
            case 6:
                num = 1000;
                break;
            case 7:
                num = 10000;
                break;
            case 8:
                num = 100000000;
                break;
            case 3:
                num = 1;
                break;
            case 2:
                num = 0.1;
                break;
            case 1:
                num = 0.01;
                break;
            case 0:
                num = 0.001;
                break;
            default:
                break;
        }
        return new BigDecimal(num);
    }

    public static void main(String[] args) {
        double x = 2066666.435;
//        double y = 59.0;
        System.out.println(x);
        String uppercaseBalance = toUppercase(x);
        System.out.println(uppercaseBalance);
        BigDecimal lowercase = toLowercase(uppercaseBalance);
        System.out.println(lowercase);
    }
}
