package top.xinsin.dao;

import com.alibaba.fastjson.JSON;
import top.xinsin.pojo.Data;
import top.xinsin.pojo.JsonPersonBean;
import top.xinsin.util.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelManagementDao extends BaseDao {
    public String getPerson() throws SQLException {
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
       return JSON.toJSONString(list);
    }
}
