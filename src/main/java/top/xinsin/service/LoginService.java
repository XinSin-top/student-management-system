package top.xinsin.service;

import com.alibaba.fastjson.JSON;
import top.xinsin.dao.LoginDao;
import top.xinsin.pojo.Login;

import java.io.IOException;
import java.io.InputStreamReader;

public class LoginService {
    public static String login(InputStreamReader inputStreamReader) throws IOException {
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len =inputStreamReader.read()) != -1){
            sb.append((char)len);
        }
        String login = LoginDao.login(JSON.parseObject(sb.toString(), Login.class));
        return login;
    }
}
