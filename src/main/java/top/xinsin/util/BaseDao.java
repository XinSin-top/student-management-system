package top.xinsin.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 通用的dao，自己写的所有的dao都继承此类;
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块加载
    static {
        Properties p = new Properties();
        try {
            p.load(BaseDao.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = p.getProperty("driver");
        url = p.getProperty("url");
        username = p.getProperty("username");
        password = p.getProperty("password");
    }

    //获取数据库连接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    //查询公共方法
    public static ResultSet execute(Connection connection,String sql,Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1 ,params[i]);
        }
        return preparedStatement.executeQuery();
    }

    //增删改方法
    public static int execute(Connection connection,String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1 ,params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    //关闭所有链接
    public static boolean closeAll(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                flag = false;
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException e) {
                flag = false;
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException e) {
                flag = false;
                e.printStackTrace();
            }
        }
        return flag;
    }
}
