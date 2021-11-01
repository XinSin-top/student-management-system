package top.xinsin;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {
    @Test
    public void data(){
        LocalDateTime now = LocalDateTime.now();
        String s =  now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(s);
    }
}
