package 事务;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

//JDBC会自动进行一条语句的提交，再进行事务操作的时候会自动提交每一天sql语句
public class Commit_false {
    public static void main(String[] args) {
        Map<String, String> userlogin = initUI();
        boolean flag = login(userlogin);
    }

    private static boolean login(Map<String, String> userlogin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("idbc");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = bundle.getString("url");
            String username = bundle.getString("username");
            String password = bundle.getString("password");

            connection = DriverManager.getConnection(url, username, password);
            //将自动提交设置为手动提交,这个函数的默认值是true,也就是自动提交
            connection.setAutoCommit(false);    //开启事务，手动提交
            String sql_statement = "update t_bank_acount set balance=? where username=?";
            preparedStatement=connection.prepareStatement(sql_statement);

            preparedStatement.setDouble(1,10000);
            preparedStatement.setString(2,"Jack");
            int count=preparedStatement.executeUpdate();

            preparedStatement.setDouble(1,10000);
            preparedStatement.setString(2,"Justin");
            count+=preparedStatement.executeUpdate();

            System.out.println(count==2?"转账成功":"转账失败");
            //程序既然能够走到这里就说明程序是没有问题的，
            connection.commit();            //提交事务
            if(count==2){
                return true;
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if(connection!=null){
                try {
                    connection.rollback();  //事务回滚
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return false;

    }

    private static Map<String, String> initUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("用户名：");
        String loginName = scanner.nextLine();
        System.out.println("密码：");
        String loginPwd = scanner.nextLine();
        Map<String, String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);
        return userLoginInfo;

    }
}
