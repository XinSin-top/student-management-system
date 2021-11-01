package top.xinsin.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import top.xinsin.pojo.Login;
import top.xinsin.pojo.t_login_all;
import top.xinsin.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LoginDao extends BaseDao {
    public static String login(Login login){
        Connection connection = getConnection();
        ResultSet execute = null;
        try {
            execute = execute(connection, "select sa_id,sa_name,sa_account,sa_password,sa_authority from student_management.t_login_all where sa_account=?;", login.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String name = null;
        String auth = null;
        boolean isLogin = false;
        String lastLoginTime = null;
        int id = 0;
//{"name":"islogin":true,"auth":"admin","lastlogintime":""}
        try {
            if (execute.next()){
                if(login.getPassword().equals(execute.getString("sa_password"))){
                    isLogin = true;
                    id = execute.getInt("sa_id");
                    auth = execute.getString("sa_authority");
                    name = execute.getString("sa_name");
                    lastLoginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Map<String, Object> json = toJson(id, isLogin, name, auth, lastLoginTime);
        String s = JSON.toJSONString(json);
//        System.out.println("即将返回前端的json表:[" + s + "]");
        json.clear();
        json = null;
        return s;
    }
    //格式化json字段
    private static Map<String ,Object> toJson(int id,boolean isLogin,String name,String auth,String lastLoginTime){
        Map<String ,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("isLogin",isLogin);
        map.put("name",name);
        map.put("auth",auth);
        map.put("lastLoginTime",lastLoginTime);
        return map;
    }
}
