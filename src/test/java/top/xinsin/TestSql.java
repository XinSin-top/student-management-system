package top.xinsin;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import top.xinsin.pojo.Data;
import top.xinsin.pojo.JsonPersonBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static top.xinsin.BaseDao.execute;
import static top.xinsin.BaseDao.getConnection;

public class TestSql {
    @Test
    public void test() throws SQLException {
        Connection connection = getConnection();
        ResultSet res = null;
        res = execute(connection,"SELECT * FROM student_management.t_login_all;");
        StringBuilder sb = new StringBuilder();
        sb.append("{'count': ,'data':[");
        while (res.next()){
            sb.append("{'id':"+ res.getString(1) + ",");
            sb.append("'name':"+ res.getString(2) + ",");
            sb.append("'account':"+ res.getString(3) + ",");
            sb.append("'password':"+ res.getString(4) + ",");
            sb.append("'lastLogin':"+ res.getString(5) + ",");
            sb.append("'auth':"+ res.getString(6) + ",},");
        }
        sb.append("]}");
        System.out.println(sb);
    }
    @Test
   public void Test01() throws SQLException {
        Connection connection = getConnection();
        ResultSet res = null;
        res = execute(connection,"SELECT * FROM student_management.t_login_all;");
        List<Data> list = new ArrayList<>();
        while (res.next()){
            Data data = new Data();
            data.setId(Integer.parseInt(res.getString(1)));
            data.setName(res.getString(2));
            data.setAccount(res.getString(3));
            data.setPassword(res.getString(4));
            data.setLastLogin(res.getString(5));
            data.setAuth(res.getString(6));
            list.add(data);
        }

        String s = JSON.toJSONString(list);
        System.out.println(s);
    }
}
