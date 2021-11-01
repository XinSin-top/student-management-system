package top.xinsin;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class TestLogin {
    @Test
    public void Login(){
        String s = JSON.toJSONString(new t_login_all(true, null, null, null));
        System.out.println(s);
    }
}
