package top.xinsin;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUpdate extends BaseDao{
    @Test
    public void update() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student_management.t_login_all SET sa_lastLogin = ? WHERE sa_id = ?;");
        try {
            int execute = execute(connection, preparedStatement,"UPDATE student_management.t_login_all SET sa_lastLogin = ? WHERE sa_id = ?;", "2021-11-01 15:34:26", 18);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
