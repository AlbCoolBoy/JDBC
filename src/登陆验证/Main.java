package 登陆验证;

//使用JDBC实现一个简单的登录功能，使用了资源绑定器
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String,String> userlogin=initUI();
        boolean flag=login(userlogin);
        if(flag==true){
            System.out.println("登录成功");
        }else{
            System.out.println("登陆失败");
        }
    }

    private static boolean login(Map<String, String> userlogin) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url=bundle.getString("url");
            String username=bundle.getString("username");
            String password=bundle.getString("password");

            connection=DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
            String sql_statement="select * from t_user where username='"+userlogin.get("loginName")+"' and password='"+userlogin.get("loginPwd")+"'";
            resultSet=statement.executeQuery(sql_statement);
            if(resultSet.next()){       //这里甚至就不需要使用while循环了，因为上面的只能查出一条数据，只要结果集中有数据，就说明登录信息是正确的
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //记得释放资源
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private static Map<String, String> initUI() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("用户名");
        String loginName= scanner.nextLine();
        System.out.println("密码");
        String loginPwd= scanner.nextLine();
        Map<String,String> userLoginInfo=new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);
        return userLoginInfo;
    }

}
//防止sql注入：
//