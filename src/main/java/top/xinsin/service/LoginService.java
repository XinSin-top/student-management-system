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
//        System.out.println("前端返回post请求的json表直接输出:[" + sb + "]");
//        Login login = JSON.parseObject(sb.toString(), Login.class);
//        System.out.println("前端返回的post请求的json表转换为实体类输出:[" + "name=" + login.getName() + "]");
//        System.out.println("前端返回的post请求的json表转换为实体类输出:[" + "username=" + login.getUsername() + "]");
//        System.out.println("前端返回的post请求的json表转换为实体类输出:[" + "password=" + login.getPassword() + "]");
//        System.out.println("前端返回的post请求的json表转换为实体类输出:[" + "authority=" + login.getAuthority() + "]");
        return LoginDao.login(JSON.parseObject(sb.toString(), Login.class));
    }
}
