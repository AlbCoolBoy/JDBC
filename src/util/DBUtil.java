package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*JDBC工具类：简化JDBC编程*/
public class DBUtil {
    /*工具类中的方法建议直接私有化
     * 因为工具类中的方法都是静态的，不需要实例化对象，直接采类名调用即可*/

    private DBUtil() {

    }

    //静态代码块，在类加载的时候执行，并且只执行一次
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getconnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_store","root","smt020528");
    }

    //实际上并不需要像下面这样，因为这些驱动再使用的时候只需要加载一次即可，所以可以直接使用静态代码块

    /*public static Connection getconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
            Connection connection= DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"), bundle.getString("password") );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
}

