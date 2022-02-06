package 登陆验证;

import javax.management.relation.RelationSupport;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

//解决sql注入问题
//  解决该问题最核心的办法就是不让用户输入的数据参与sql语句的编译过程
//使用预编译的数据库操作对象
public class prevent_sql_injection {
    public static void main(String[] args) {
        Map<String, String> userlogin = initUI();
        boolean flag = login(userlogin);
        if(flag==true){
            System.out.println("登录成功");
        }else{
            System.out.println("登陆失败");
        }

    }

    private static boolean login(Map<String, String> userlogin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = bundle.getString("url");
            String username = bundle.getString("username");
            String password = bundle.getString("password");

            connection = DriverManager.getConnection(url, username, password);
            //获取预编译的数据库操作对象,这里的?是占位符，是框架中用的
            String sql_statement = "select * from t_user where username=?and password=? ";
            preparedStatement = connection.prepareStatement(sql_statement);     //预处理上面的sql框架语句
            //程序运行到这里的时候就会发送sql语句给DBMS，然后DBMS会对语句进行预编译

            //从这里开始就要给框架中的占位符传递值了，其中第一个问号再JDBC中下标是1，第二个问号下标是2，以此类推
            preparedStatement.setString(1,userlogin.get("loginName"));
            preparedStatement.setString(2,userlogin.get("loginPwd"));
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //依旧释放资源
        finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
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
//preparesdStatement的效率更高
//首先它解决了sql注入的问题，另外由于进行了预编译，再次执行的时候不需要再进行编译了，直接执行即可