package string;

import org.apache.commons.lang3.StringUtils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StringContainsTest {

    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String extractDateTime = fmt.format(ZonedDateTime.now(ZoneOffset.UTC));
        System.out.println(extractDateTime);
    }

}
