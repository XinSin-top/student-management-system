package top.xinsin.dao;

import top.xinsin.util.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDDao extends BaseDao {
    public static Integer insert(String sa_name, String sa_account, String sa_password, String sa_authority) throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student_management.t_login_all(sa_name,sa_account,sa_password,sa_authority) VALUES(?,?,?,?);");
        int rs = execute(connection, preparedStatement, "INSERT INTO student_management.t_login_all(sa_name,sa_account,sa_password,sa_authority) VALUES(?,?,?,?);", sa_name, sa_account, sa_password, sa_authority);
        connection.commit();
        connection.close();
        preparedStatement.close();
        return rs;
    }
    public static Integer delete(int sa_id) throws SQLException{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student_management.t_login_all WHERE sa_id = ?;");
        int execute = execute(connection, preparedStatement, "DELETE FROM student_management.t_login_all WHERE sa_id = ?;", sa_id);
        connection.commit();
        connection.close();
        preparedStatement.close();
        return execute;
    }
    public static Integer update(int sa_id,String sa_name, String sa_account, String sa_password, String sa_authority) throws SQLException{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student_management.t_login_all SET sa_name = ?,sa_account = ?,sa_password = ?,sa_authority = ? WHERE sa_id = ?;");
        int execute = execute(connection, preparedStatement, "UPDATE student_management.t_login_all SET sa_name = ?,sa_account = ?,sa_password = ?,sa_authority = ? WHERE sa_id = ?;",sa_name,sa_account,sa_password,sa_authority,sa_id);

        connection.commit();
        connection.close();
        preparedStatement.close();
        return execute;
    }
}
