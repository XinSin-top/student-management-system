package top.xinsin.dao;

import com.alibaba.fastjson.JSON;
import top.xinsin.pojo.Login;
import top.xinsin.pojo.t_login_all;
import top.xinsin.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LoginDao {
    public static String login(Login login){
        Connection connection = BaseDao.getConnection();
        Object[] objects = new Object[1];
        objects[0] = login.getUsername();
        ResultSet execute = null;
        try {
            execute = BaseDao.execute(connection, "select sa_name,sa_account,sa_password,sa_authority from student_management.t_login_all where sa_account=?;", objects);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String name = null;
        String auth = null;
        boolean islogin = false;
//{"name":"islogin":true,"auth":"admin","lastlogintime":""}
        try {
            if (execute.next()){
                if(login.getPassword().equals(execute.getString("sa_password"))){
                    islogin = true;
                    auth = execute.getString("sa_authority");
                    name = execute.getString("sa_name");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return JSON.toJSONString(new t_login_all(islogin,name,new Date(),auth));
    }
}
