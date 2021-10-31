package top.xinsin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.junit.Test;
import top.xinsin.pojo.Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserWrite {
    @Test
    public void writeLogin(){
        Connection connection = BaseDao.getConnection();
        String fileName = "D:/Project/xinxin-project/student-management-system/src/test/resources/login.xlsx";
        EasyExcel.read(fileName, Login.class, new PageReadListener<Login>(dataList -> {
            for (Login login: dataList) {
                try {
                    Statement statement = connection.createStatement();
                    statement.execute("insert into student_management.t_login_all(sa_name,sa_account,sa_password,sa_authority) values("
                            +login.getName()+","+login.getUsername()+","+login.getPassword()+","+login.getAuthority()+");");
                    statement.execute("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        })).sheet().doRead();
    }
    @Test
    public void writeUser(){
        String fileName = "D:/Project/xinxin-project/student-management-system/src/test/resources/user.xlsx";
        EasyExcel.read(fileName, t_basic_student.class, new PageReadListener<t_basic_student>(dataList -> {
            for (t_basic_student login: dataList) {
                System.out.println(login.getS_sex());
            }
        })).sheet().doRead();
    }
}
