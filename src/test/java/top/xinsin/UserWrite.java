package top.xinsin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.junit.Test;

import java.sql.*;

public class UserWrite extends BaseDao {
    @Test
    public void writeLogin(){
        String fileName = "src/test/resources/login.xlsx";
        EasyExcel.read(fileName, Login.class, new PageReadListener<Login>(dataList -> {
            for (Login login: dataList) {
                Connection connection = getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into student_management.t_login_all(sa_name,sa_account,sa_password,sa_authority) values(?,?,?,?);");
                    execute(connection,preparedStatement,"insert into student_management.t_login_all(sa_name,sa_account,sa_password,sa_authority) values(?,?,?,?);",login.getSa_name(),login.getSa_account(),login.getSa_password(),login.getSa_authority());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        })).sheet().doRead();
    }
    /*private void writer(Object... params){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management?serverTimezone=UTC-8","root","123456");
            PreparedStatement preparedStatement = connection.prepareStatement();
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1 ,params[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
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
